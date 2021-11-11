package com.example.trabalhofinal.db.repository;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.trabalhofinal.db.annotation.ForeignKey;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.db.connector.DatabaseConnector;
import com.example.trabalhofinal.util.StringUitl;

class CreateTableRepository {
	private final Logger logger = Logger.getAnonymousLogger();

	private final Class<?> tClass;

	CreateTableRepository(Class<?> tClass) {
		this.tClass = tClass;
		validaClass();
		tentarCriarTabela();
	}

	private void tentarCriarTabela() {
		try {
			criarTabela();
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
		throw new IllegalArgumentException("Classe informada não possui a anotação de " + Table.class.getName());
	}

	public boolean criarTabela() throws SQLException, ClassNotFoundException {
		return criarTabela(tClass, validaClass());
	}

	private boolean criarTabela(Class<?> tClass) throws SQLException, ClassNotFoundException {
		return criarTabela(tClass, validaClass());
	}

	private Boolean criarTabela(Class<?> tClass, Table annotation) throws SQLException, ClassNotFoundException {
		final TableQuery tableQuery = new TableQuery(annotation.name());

		for (Field field : tClass.getDeclaredFields()) {
			gerarQueryDeAtributo(tableQuery, field);
		}

		try (final Statement statement = DatabaseConnector.connector
				.getConnection()
				.createStatement()) {
			final String sql = tableQuery.asQuery();
			logger.log(Level.INFO, sql);
			return statement.execute(sql);
		}
	}

	private void gerarQueryDeAtributo(TableQuery tableQuery, Field field) throws SQLException, ClassNotFoundException {
		final Property property = field.getAnnotation(Property.class);
		final Table relationShip = field.getType().getAnnotation(Table.class);

		if (relationShip != null) {
			configuraModeloDeRelacionamento(tableQuery, field, relationShip);
		} else {
			configuraQueryDeAtributo(tableQuery, field, property);
		}
	}

	private void configuraQueryDeAtributo(TableQuery tableQuery, Field field, Property property) {
		String type = StringUitl.toSnakeCase(field.getType().getSimpleName())
				.toUpperCase()
				.replace("STRING", "VARCHAR(255)");

		if (property != null) {
			type = property.type().isBlank() ? type : property.type();
			type = property.primaryKey() ? "INT PRIMARY KEY AUTO_INCREMENT" : type;
			tableQuery.addFieldQuery(property.name(), type);
		} else {
			tableQuery.addFieldQuery(StringUitl.toSnakeCase(field.getName()), type);
		}
	}

	private void configuraModeloDeRelacionamento(TableQuery tableQuery, Field field, Table relationShip) throws SQLException, ClassNotFoundException {
		final ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
		if (foreignKey == null) {
			throw new IllegalCallerException("Uma relação entre classes deve possuir a anotação " + ForeignKey.class.getName());
		}
		criarTabela(field.getType(), relationShip);

		final StringBuilder foreignProperties = new StringBuilder("INT");

		if (foreignKey.required()) {
			foreignProperties.append(" NOT NULL");
		}

		if (foreignKey.unique()) {
			foreignProperties.append(" UNIQUE");
		}

		String foreignColumnName = foreignKey.columnName().isBlank() ? StringUitl.toSnakeCase(field.getName()) + "_id" : foreignKey.columnName();

		tableQuery.addFieldQuery(foreignColumnName, foreignProperties.toString());

		tableQuery.addConstraint("FOREIGN KEY ("
				+ foreignColumnName + ") REFERENCES "
				+ relationShip.name()
				+ "("
				+ foreignKey.foreignKeyName()
				+ ")");
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
