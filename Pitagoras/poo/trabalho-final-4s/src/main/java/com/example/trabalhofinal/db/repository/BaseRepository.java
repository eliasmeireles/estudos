package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.util.GenericsClassUtil.getGenericTypeClass;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.trabalhofinal.db.adapter.Adapter;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.PropertyAdapter;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.db.connector.DatabaseConnector;
import com.example.trabalhofinal.util.StringUitl;

public abstract class BaseRepository<T> {

	private final Logger logger = Logger.getAnonymousLogger();

	private final Class<T> tClass;
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

	public List<T> findAll() {
		return findAll(null);
	}

	public List<T> findAll(String query, Object... params) {
		List<T> result = new ArrayList<>();
		String fullQuery = selectAllQuery;

		if (query != null) {
			fullQuery = selectAllQuery.concat(" ").concat(query);
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

	public boolean savar(T objeto) throws Exception {

		final StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
				.append(nomeTable)
				.append("(");

		final StringBuilder params = new StringBuilder(") VALUES (");
		final Map<Integer, Object> valores = new HashMap<>();

		int contador = 1;
		for (Field field : objeto.getClass().getDeclaredFields()) {
			final Property property = field.getAnnotation(Property.class);
			final String keyName = property != null ? property.name() : StringUitl.toSnakeCase(field.getName());
			if (property == null || !property.primaryKey()) {
				insertQuery.append(keyName)
						.append(", ");
				params.append("?, ");

				valores.put(contador, obterValorDoField(field, objeto));
				contador++;
			}
		}

		final String queryKeys = insertQuery.substring(0, insertQuery.length() - 2);
		final String query = queryKeys + params.substring(0, params.length() - 2) + ")";

		return executarInsertUpdateQuerySql(valores, query);
	}

	public boolean atualizar(T objeto) throws Exception {
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

		valores.put(contador + 1, pk);

		String fullSql = updateSql.substring(0, updateSql.length() - 2) + " WHERE " + nomePk + " = ?";

		return executarInsertUpdateQuerySql(valores, fullSql);
	}

	private boolean executarInsertUpdateQuerySql(Map<Integer, Object> valores, String query) throws SQLException, ClassNotFoundException {
		logger.log(Level.INFO, query);

		try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
			for (Map.Entry<Integer, Object> valor : valores.entrySet()) {
				statement.setObject(valor.getKey(), valor.getValue());
			}
			statement.execute();
		}
		return true;
	}

	private Object obterValorDoField(Field field, Object objeto) throws Exception {
		Object value;
		if (field.canAccess(objeto)) {
			value = field.get(objeto);
		} else {
			field.setAccessible(true);
			value = field.get(objeto);
			field.setAccessible(false);
		}
		final PropertyAdapter annotation = field.getAnnotation(PropertyAdapter.class);

		if (annotation != null) {
			final Adapter<?> instance = (Adapter<?>) annotation.adapter().getConstructors()[0].newInstance();
			return instance.fromObject(value);
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	protected T newInstanceFromResult(ResultSet resultSet) throws Exception {
		T newInstance = (T) tClass.getConstructors()[0].newInstance();

		for (Field field : newInstance.getClass().getDeclaredFields()) {
			final Property property = field.getAnnotation(Property.class);
			final String keyName = property != null ? property.name() : StringUitl.toSnakeCase(field.getName());
			final Object value = resultSet.getObject(keyName);

			if (field.canAccess(newInstance)) {
				field.set(newInstance, valuedAdatper(field, value));
			} else {
				field.setAccessible(true);
				field.set(newInstance, valuedAdatper(field, value));
				field.setAccessible(false);
			}
		}
		return newInstance;
	}

	@SuppressWarnings("unchecked")
	private <T> T valuedAdatper(Field field, Object value) throws Exception {
		final PropertyAdapter annotation = field.getAnnotation(PropertyAdapter.class);

		if (annotation != null) {
			final Adapter<T> instance = (Adapter<T>) annotation.adapter().getConstructors()[0].newInstance();
			return instance.toObject(value);
		}
		return (T) value;
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
