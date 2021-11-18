package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDoAtributoNoBanco;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehAtributoSimple;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehCollection;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehOneToMany;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehOneToOne;
import static com.example.trabalhofinal.util.GenericsClassUtil.getGenericTypeClass;
import static com.example.trabalhofinal.util.GenericsClassUtil.obterValorDoField;
import static com.example.trabalhofinal.util.GenericsClassUtil.valuedAdapter;

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

import com.example.trabalhofinal.db.annotation.OneToMany;
import com.example.trabalhofinal.db.annotation.OneToOne;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.db.connector.DatabaseConnector;
import com.example.trabalhofinal.util.StringUitl;

@SuppressWarnings("unchecked") public abstract class BaseRepository<T, ID> {

	private final Logger logger = Logger.getAnonymousLogger();

	protected final Class<T> tClass;
	protected final String selectAllQuery;
	protected final String nomeTable;
	private final DatabaseConnector connector;

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

		int contador = 1;
		for (Field field : objeto.getClass().getDeclaredFields()) {
			final Property property = field.getAnnotation(Property.class);
			final String keyName = obterNomeDoAtributoNoBanco(field);

			if ((property == null || !property.primaryKey()) && !ehOneToMany(field)) {
				insertQuery.append(keyName)
						.append(", ");
				params.append("?, ");

				Object valor = obterValorDoField(field, objeto);

				if (ehOneToOne(field)) {
					valor = new OneToOneRepository(field)
							.salvar(obterValorDoField(field, objeto));
				}

				valores.put(contador, valor);
				contador++;
			}
		}

		final String queryKeys = insertQuery.substring(0, insertQuery.length() - 2);
		final String query = queryKeys + params.substring(0, params.length() - 2) + ")";

		return executarInsertUpdateQuerySql(valores, query);
	}

	public ID atualizar(T objeto) throws Exception {
		final StringBuilder updateSql = new StringBuilder("UPDATE ")
				.append(nomeTable)
				.append(" SET ");

		final Map<Integer, Object> valores = new HashMap<>();

		String nomePk = "";
		Object pk = null;
		int contador = 1;
		for (Field field : objeto.getClass().getDeclaredFields()) {
			final Property property = field.getAnnotation(Property.class);
			final String keyName = property != null ? property.name() : StringUitl.toSnakeCase(field.getName());

			if (property == null || !property.primaryKey()) {
				updateSql.append(keyName)
						.append(" = ?, ");

				valores.put(contador, obterValorDoField(field, objeto));
				contador++;
			}

			if (property != null && property.primaryKey()) {
				pk = obterValorDoField(field, objeto);
				nomePk = property.name();
			}
		}

		String fullSql = updateSql.substring(0, updateSql.length() - 2) + " WHERE " + nomePk + " = " + pk;
		executarInsertUpdateQuerySql(valores, fullSql);
		return (ID) pk;
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

	@SuppressWarnings("unchecked")
	protected T newInstanceFromResult(ResultSet resultSet) throws Exception {
		final Constructor<?> constructor = tClass.getConstructors()[0];
		T newInstance = (T) constructor.newInstance();

		carregarAtributos(resultSet, newInstance);
		return newInstance;
	}

	private Field carregarAtributos(ResultSet resultSet, T newInstance) throws Exception {
		Field fieldPk = null;
		Object valuePk = null;
		for (Field field : newInstance.getClass().getDeclaredFields()) {
			final String keyName = obterNomeDoAtributoNoBanco(field);

			if (ehAtributoSimple(field)) {
				Object value = resultSet.getObject(keyName);
				final Property property = field.getAnnotation(Property.class);
				fieldPk = property != null && property.primaryKey() ? field : fieldPk;
				valuePk = property != null && property.primaryKey() ? value : valuePk;
				adicionarValorDoAtributo(newInstance, field, value);
			} else if (field.getAnnotation(OneToOne.class) != null) {
				Object value = resultSet.getObject(keyName);
				final OneToOneRepository oneToOneRepository = new OneToOneRepository(field);
				adicionarValorDoAtributo(newInstance, field, oneToOneRepository.find(value));
			} else if (ehCollection(fieldPk, valuePk, field)) {
				final OneToManyRepository oneToManyRepository =
						new OneToManyRepository(newInstance.getClass(), field.getAnnotation(OneToMany.class).target(), field, fieldPk, valuePk);
				adicionarValorDoAtributo(newInstance, field, oneToManyRepository.find());
			}
		}
		if (fieldPk == null) {
			throw new IllegalCallerException("Id da class " + tClass.getName() + " não encontrado.");
		}
		return fieldPk;
	}

	private void adicionarValorDoAtributo(T newInstance, Field field, Object value) throws Exception {
		if (field.canAccess(newInstance)) {
			field.set(newInstance, valuedAdapter(field, value));
		} else {
			field.setAccessible(true);
			field.set(newInstance, valuedAdapter(field, value));
			field.setAccessible(false);
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
}
