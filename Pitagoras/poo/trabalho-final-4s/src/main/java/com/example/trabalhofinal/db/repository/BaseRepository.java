package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDoAtributoNoBanco;
import static com.example.trabalhofinal.util.GenericsClassUtil.adicionarValorDoAtributo;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehAtributoSimple;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehOneToMany;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehOneToOne;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehPk;
import static com.example.trabalhofinal.util.GenericsClassUtil.findAllCollectionMembers;
import static com.example.trabalhofinal.util.GenericsClassUtil.findObjectPk;
import static com.example.trabalhofinal.util.GenericsClassUtil.getGenericTypeClass;
import static com.example.trabalhofinal.util.GenericsClassUtil.obterValorDoField;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.trabalhofinal.db.annotation.OneToOne;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.db.connector.DatabaseConnector;
import com.example.trabalhofinal.util.GenericsClassUtil;

@SuppressWarnings("unchecked") public abstract class BaseRepository<T, ID> {

	private final Logger logger = Logger.getAnonymousLogger();

	protected final Class<T> tClass;
	protected final String selectAllQuery;
	protected final String nomeTable;
	protected final DatabaseConnector connector;

	protected BaseRepository() {
		connector = DatabaseConnector.connector;
		this.tClass = getGenericTypeClass(getClass());
		new CreateTableRepository(tClass);
		nomeTable = getNomeTable();
		this.selectAllQuery = new QueryBuilder(tClass, nomeTable).build();
	}

	protected BaseRepository(Class<?> tClass) {
		connector = DatabaseConnector.connector;
		this.tClass = (Class<T>) tClass;
		new CreateTableRepository(tClass);
		nomeTable = getNomeTable();
		this.selectAllQuery = new QueryBuilder(tClass, nomeTable).build();
	}

	public List<T> findAll() {
		return findAll(null);
	}

	public List<T> findAll(StringBuilder query, Object... params) {
		List<T> result = new ArrayList<>();
		String fullQuery = selectAllQuery;

		if (query != null) {
			fullQuery = selectAllQuery.concat(" ").concat(query.toString());
		}

		try (PreparedStatement statement = connector.getConnection().prepareStatement(fullQuery)) {
			logger.log(Level.INFO, fullQuery);
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			final ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				result.add(newInstanceFromResult(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ID salvar(T objeto) throws Exception {

		final StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
				.append(nomeTable)
				.append("(");

		final StringBuilder params = new StringBuilder(") VALUES (");
		final Map<Integer, Object> valores = new HashMap<>();

		final List<Field> collectionsField = new ArrayList<>();
		int contador = 1;
		for (Field field : objeto.getClass().getDeclaredFields()) {
			final Property property = field.getAnnotation(Property.class);
			final String keyName = obterNomeDoAtributoNoBanco(field);

			if ((property == null || !property.primaryKey()) && !ehOneToMany(field)) {
				insertQuery.append(keyName)
						.append(", ");
				params.append("?, ");

				contador = atribuiValorDeAtributo(objeto, valores, contador, field);
			} else if (ehOneToMany(field)) {
				collectionsField.add(field);
			}
		}

		final String queryKeys = insertQuery.substring(0, insertQuery.length() - 2);
		final String query = queryKeys + params.substring(0, params.length() - 2) + ")";

		ID id = executarInsertUpdateQuerySql(valores, query);
		for (Field field : collectionsField) {
			OneToManyRepository oneToManyRepository = new OneToManyRepository(objeto, field);
			oneToManyRepository.insertInto(id);
		}
		return id;
	}

	public ID atualizar(T objeto) throws Exception {
		final StringBuilder updateSql = new StringBuilder("UPDATE ")
				.append(nomeTable)
				.append(" SET ");

		final Map<Integer, Object> valores = new HashMap<>();

		GenericsClassUtil.PrimaryKeyData<Object> primaryKeyData = findObjectPk(objeto);
		int contador = 1;
		for (Field field : objeto.getClass().getDeclaredFields()) {
			final Property property = field.getAnnotation(Property.class);
			final String keyName = obterNomeDoAtributoNoBanco(field);

			if ((property == null || !property.primaryKey()) && !ehOneToMany(field)) {
				updateSql.append(keyName)
						.append(" = ?, ");

				contador = atribuiValorDeAtributo(objeto, valores, contador, field);
			}
		}

		String fullSql = updateSql.substring(0, updateSql.length() - 2) + " WHERE " + primaryKeyData.getPkName() + " = " + primaryKeyData.getPkValue();
		executarInsertUpdateQuerySql(valores, fullSql);
		oneToManyDeleteMode((ID) primaryKeyData.getPkValue(), objeto);
		return (ID) primaryKeyData.getPkValue();
	}

	private int atribuiValorDeAtributo(T objeto, Map<Integer, Object> valores, int contador, Field field) throws Exception {
		Object valor = obterValorDoField(field, objeto);

		if (ehOneToOne(field)) {
			valor = new OneToOneRepository(field).salvar(obterValorDoField(field, objeto));
		}

		valores.put(contador, valor);
		contador++;
		return contador;
	}

	public void deleteById(ID id) throws Exception {
		final GenericsClassUtil.PrimaryKeyData<Object> primaryKeyData = findObjectPk(newInstance());
		deleteById(id, nomeTable, primaryKeyData.getPkName());
	}

	public void deleteById(ID id, String pkName) throws Exception {
		deleteById(id, nomeTable, pkName);
	}

	public void deleteById(ID id, String nomeTable, String pkName) throws Exception {

		oneToManyDeleteMode(id);
		final StringBuilder query = new StringBuilder("DELETE FROM ")
				.append(nomeTable)
				.append(" WHERE ")
				.append(fieldFilter(pkName));

		delete(query, id);
	}

	public void delete(StringBuilder query, Object... params) throws Exception {
		try (final PreparedStatement statement = connector.getConnection().prepareStatement(query.toString())) {
			int contador = 1;

			for (Object param : params) {
				statement.setObject(contador, param);
				contador++;
			}
			statement.execute();
		}
	}

	private void oneToManyDeleteMode(ID id) throws Exception {
		oneToManyDeleteMode(id, newInstance());
	}

	private void oneToManyDeleteMode(ID id, Object parent) throws Exception {
		for (Field field : findAllCollectionMembers(parent)) {
			OneToManyRepository oneToManyRepository = new OneToManyRepository(parent, field);
			oneToManyRepository.collectionDelete(id);
			oneToManyRepository.insertInto(id);
		}
	}

	private ID executarInsertUpdateQuerySql(Map<Integer, Object> valores, String query) throws SQLException, ClassNotFoundException {
		logger.log(Level.INFO, query);

		try (PreparedStatement statement = connector.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			for (Map.Entry<Integer, Object> valor : valores.entrySet()) {
				statement.setObject(valor.getKey(), valor.getValue());
			}
			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return (ID) resultSet.getString(1);
			}
		}
		return null;
	}

	private void carregarAtributos(ResultSet resultSet, T newInstance) throws Exception {
		GenericsClassUtil.PrimaryKeyData<Object> primaryKeyData = null;

		for (Field field : newInstance.getClass().getDeclaredFields()) {
			setValorDoAtributo(resultSet, newInstance, primaryKeyData, field);
			if (ehPk(field)) {
				String pkName = obterNomeDoAtributoNoBanco(field);
				primaryKeyData = new GenericsClassUtil.PrimaryKeyData<>(resultSet.getObject(pkName), pkName);
			}
		}

		if (primaryKeyData == null) {
			throw new IllegalCallerException("Id da class " + tClass.getName() + " não encontrado.");
		}
	}

	private void setValorDoAtributo(ResultSet resultSet, T newInstance,
			GenericsClassUtil.PrimaryKeyData<Object> primaryKeyData, Field field) throws Exception {
		final String keyName = obterNomeDoAtributoNoBanco(field);

		if (ehAtributoSimple(field)) {
			Object value = resultSet.getObject(keyName);
			adicionarValorDoAtributo(newInstance, field, value);
		} else if (field.getAnnotation(OneToOne.class) != null) {
			Object value = resultSet.getObject(keyName);
			final OneToOneRepository oneToOneRepository = new OneToOneRepository(field);
			adicionarValorDoAtributo(newInstance, field, oneToOneRepository.find(value));
		} else if (ehOneToMany(field) && primaryKeyData != null) {
			final OneToManyRepository oneToManyRepository = new OneToManyRepository(newInstance, field);
			Object values = oneToManyRepository.find(primaryKeyData.getPkValue());
			adicionarValorDoAtributo(newInstance, field, values);
		}
	}

	protected String fieldFilter(String nome) {
		return nomeTable + "." + nome + " = ?";
	}

	private String getNomeTable() {
		try {
			return tClass.getAnnotation(Table.class).name();
		} catch (Exception e) {
			throw new IllegalArgumentException("Classe informada não possui a anotação de " + Table.class.getName());
		}
	}

	protected T newInstanceFromResult(ResultSet resultSet) throws Exception {
		T newInstance = newInstance();
		carregarAtributos(resultSet, newInstance);
		return newInstance;
	}

	protected T newInstance() throws Exception {
		final Constructor<?> constructor = tClass.getConstructors()[0];
		return (T) constructor.newInstance();
	}
}
