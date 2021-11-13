package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.util.GenericsClassUtil.getGenericTypeClass;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.db.connector.DatabaseConnector;
import com.example.trabalhofinal.util.StringUitl;

public abstract class BaseRepository<T> {

	private final Logger logger = Logger.getAnonymousLogger();

	private final Class<T> tClass;
	public final String selectAllQuery;
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

	protected List<T> findAll(String query, Object... params) {
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

	public boolean savar(T objeto) {
		try {
			final StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
					.append(nomeTable)
					.append("(");

			final StringBuilder params = new StringBuilder(") VALUES (");
			final List<Object> valores = new ArrayList<>();

			for (Field field : objeto.getClass().getDeclaredFields()) {
				final Property property = field.getAnnotation(Property.class);
				final String keyName = property != null ? property.name() : StringUitl.toSnakeCase(field.getName());
				if (property == null || !property.primaryKey()) {
					insertQuery.append(keyName)
							.append(", ");
					params.append("?, ");

					valores.add(obterValorDoField(field, objeto));
				}
			}

			final String queryKeys = insertQuery.substring(0, insertQuery.length() - 2);
			final String query = queryKeys +  params.substring(0, params.length() - 2) + ")";

			logger.log(Level.INFO, query);

			try (PreparedStatement statement = connector.getConnection().prepareStatement(query)) {
				for (Object valor : valores) {
					statement.setObject(valores.indexOf(valor) + 1, valor);
				}
				return statement.execute();
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return false;
	}

	public boolean atualizar(T objeto) {
		try {
			final StringBuilder updateSql = new StringBuilder("UPDATE ")
					.append(nomeTable)
					.append(" SET ");

			final List<Object> valores = new ArrayList<>();

			String nomePk = "";
			Object pk = null;

			for (Field field : objeto.getClass().getDeclaredFields()) {
				final Property property = field.getAnnotation(Property.class);
				final String keyName = property != null ? property.name() : StringUitl.toSnakeCase(field.getName());

				if (property == null || !property.primaryKey()) {
					updateSql.append(keyName)
							.append(" = ?, ");

					valores.add(obterValorDoField(field, objeto));
				}

				if (property != null && property.primaryKey()) {
					pk = obterValorDoField(field, objeto);
					nomePk = property.name();
				}
			}

			valores.add(pk);

			String fullSql = updateSql.substring(0, updateSql.length() - 2) + " WHERE " + nomePk + " = ?";

			logger.log(Level.INFO, fullSql);
			try (PreparedStatement statement = connector.getConnection().prepareStatement(fullSql)) {
				for (Object valor : valores) {
					statement.setObject(valores.indexOf(valor) + 1, valor);
				}
				return statement.execute();
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return false;
	}

	private Object obterValorDoField(Field field, Object objeto) throws IllegalAccessException {
		if (field.canAccess(objeto)) {
			return field.get(objeto);
		} else {
			field.setAccessible(true);
			Object value = field.get(objeto);
			field.setAccessible(false);
			return value;
		}
	}

	@SuppressWarnings("unchecked")
	protected T newInstanceFromResult(ResultSet resultSet) throws Exception {
		T newInstance = (T) tClass.getConstructors()[0].newInstance();

		for (Field field : newInstance.getClass().getDeclaredFields()) {
			final Property property = field.getAnnotation(Property.class);
			final String keyName = property != null ? property.name() : StringUitl.toSnakeCase(field.getName());
			final Object value = resultSet.getObject(keyName);

			if (field.canAccess(newInstance)) {
				field.set(newInstance, value);
			} else {
				field.setAccessible(true);
				field.set(newInstance, value);
				field.setAccessible(false);
			}
		}
		return newInstance;
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
