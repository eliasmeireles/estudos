package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.fieldType;
import static com.example.trabalhofinal.db.repository.util.QueryUtil.foreingKeyQuery;
import static com.example.trabalhofinal.db.repository.util.QueryUtil.gerarQueryTableCollection;
import static com.example.trabalhofinal.util.GenericsClassUtil.ehAtributoSimple;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.trabalhofinal.db.annotation.OneToMany;
import com.example.trabalhofinal.db.annotation.OneToOne;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.db.connector.DatabaseConnector;
import com.example.trabalhofinal.db.repository.util.QueryUtil;
import com.example.trabalhofinal.util.StringUitl;

class CreateTableRepository {
	private final Logger logger = Logger.getAnonymousLogger();

	private final Class<?> tClass;
	private final List<String> queries = new ArrayList<>();

	CreateTableRepository(Class<?> tClass) {
		this.tClass = tClass;
		validaClass();
		tentarCriarTabela();
	}

	private void tentarCriarTabela() {
		try {
			criarQueryTabelasTabela();
		} catch (Exception exception) {
			logger.log(Level.WARNING, exception.getMessage());
		}
	}

	private Table validaClass() {
		return validaClass(this.tClass);
	}

	private Table validaClass(Class<?> tClass) {
		Table annotation = tClass.getAnnotation(Table.class);
		if (annotation != null) {
			return annotation;
		}
		throw new IllegalArgumentException("Classe " + tClass.getName() + "  informada não possui a anotação de " + Table.class.getName());
	}

	public boolean criarQueryTabelasTabela() throws SQLException, ClassNotFoundException {
		criarQueryTabelasTabela(tClass);
		try (final Statement statement = DatabaseConnector.connector
				.getConnection()
				.createStatement()) {

			for (String query : queries) {
				try {
					statement.execute(query);
					System.out.println(query);
				} catch (Exception e) {
					logger.log(Level.WARNING, e.getMessage());
				}
			}
		}
		return true;
	}

	private void criarQueryTabelasTabela(Class<?> tClass) throws SQLException, ClassNotFoundException {
		final Table table = tClass.getAnnotation(Table.class);
		final TableQuery tableQuery = new TableQuery(table.name());
		criarQueryAtributos(tClass, tableQuery);
		criarTabelaCollections(tClass);
	}

	private void criarTabelaCollections(Class<?> tClass) {
		try {
			for (Field field : tClass.getDeclaredFields()) {
				final String query = validaQueryTabelaCollection(tClass, field);
				if (query != null) {
					queries.add(query);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String validaQueryTabelaCollection(Class<?> tClass, Field field) throws SQLException, ClassNotFoundException {
		final OneToMany collection = field.getAnnotation(OneToMany.class);
		if (collection != null) {
			criarQueryTabelasTabela(collection.target());
			return gerarQueryTableCollection(tClass, collection);
		}
		return null;
	}

	private void criarQueryAtributos(Class<?> tClass, TableQuery tableQuery) throws SQLException, ClassNotFoundException {
		for (Field field : tClass.getDeclaredFields()) {
			gerarQueryDeAtributo(tableQuery, field);
		}

		queries.add(tableQuery.asQuery());
	}

	private void gerarQueryDeAtributo(TableQuery tableQuery, Field field) throws SQLException, ClassNotFoundException {
		final Table relationShip = field.getType().getAnnotation(Table.class);
		final OneToMany collections = field.getAnnotation(OneToMany.class);

		if (relationShip != null) {
			configuraModeloDeRelacionamento(tableQuery, field, relationShip);
		} else if (collections == null) {
			configuraQueryDeAtributo(tableQuery, field);
		}
	}

	private void configuraQueryDeAtributo(TableQuery tableQuery, Field field) {
		if (ehAtributoSimple(field)) {
			final QueryUtil.SqlFieldData fieldData = fieldType(field);
			tableQuery.addFieldQuery(fieldData.getColumnName(), fieldData.getColumnType());
		}
	}

	private void configuraModeloDeRelacionamento(TableQuery tableQuery, Field field, Table relationShip) throws SQLException, ClassNotFoundException {
		final OneToOne foreignKey = field.getAnnotation(OneToOne.class);
		if (foreignKey == null) {
			throw new IllegalCallerException("Uma relação entre classes deve possuir a anotação " + OneToOne.class.getName());
		}
		criarQueryTabelasTabela(field.getType());

		final StringBuilder foreignProperties = new StringBuilder("INT");

		if (foreignKey.required()) {
			foreignProperties.append(" NOT NULL");
		}

		if (foreignKey.unique()) {
			foreignProperties.append(" UNIQUE");
		}

		final String foreignKeyRefer = StringUitl.toSnakeCase(field.getName()) + "_id";
		String foreignColumnName = foreignKey.columnName().isBlank() ? foreignKeyRefer : foreignKey.columnName();

		tableQuery.addFieldQuery(foreignColumnName, foreignProperties.toString());

		final String targetFk = foreignKey.foreignKeyName().isBlank() ? foreignKeyRefer : foreignKey.foreignKeyName();

		tableQuery.addConstraint(foreingKeyQuery(foreignColumnName, relationShip.name(), targetFk));
	}

	private static class TableQuery {
		private final String tableName;
		private final List<FieldQuery> fieldQueries = new ArrayList<>();
		private final List<String> constraints = new ArrayList<>();

		public TableQuery(String tableName) {
			this.tableName = tableName;
		}

		public void addConstraint(String value) {
			constraints.add(value);
		}

		public void addFieldQuery(String fieldName, String type) {
			fieldQueries.add(new FieldQuery(fieldName, type));
		}

		public String asQuery() {
			final StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ")
					.append(tableName)
					.append("(");

			for (FieldQuery fieldQuery : fieldQueries) {
				stringBuilder.append(fieldQuery.fieldName)
						.append(" ")
						.append(fieldQuery.type)
						.append(", ");
			}

			for (String constraint : constraints) {
				stringBuilder.append(constraint)
						.append(", ");
			}

			return stringBuilder.append(")").toString().replace(", )", ")");
		}
	}

	private static class FieldQuery {
		private final String fieldName;
		private final String type;

		public FieldQuery(String fieldName, String type) {
			this.fieldName = fieldName;
			this.type = type;
		}
	}
}
