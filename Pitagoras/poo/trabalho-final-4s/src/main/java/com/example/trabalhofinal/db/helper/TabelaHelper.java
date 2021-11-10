package com.example.trabalhofinal.db.helper;

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
import com.example.trabalhofinal.db.DatabaseConnector;
import com.example.trabalhofinal.util.StringUitl;

public class TabelaHelper {

	private static final Logger logger = Logger.getAnonymousLogger();

	private TabelaHelper() {

	}

	public static boolean createTable(Class<?> tClass) throws SQLException, ClassNotFoundException {
		Table annotation = tClass.getAnnotation(Table.class);
		if (annotation != null) {
			return createTable(tClass, annotation);
		}
		throw new IllegalArgumentException("Classe informada não possui a anotação de " + Table.class.getName());
	}

	private static Boolean createTable(Class<?> tClass, Table annotation) throws SQLException, ClassNotFoundException {
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

	private static void gerarQueryDeAtributo(TableQuery tableQuery, Field field) throws SQLException, ClassNotFoundException {
		final Property property = field.getAnnotation(Property.class);
		final Table relationShip = field.getType().getAnnotation(Table.class);

		if (relationShip != null) {
			configuraModeloDeRelacionamento(tableQuery, field, relationShip);
		} else {
			configuraQueryDeAtributo(tableQuery, field, property);
		}
	}

	private static void configuraQueryDeAtributo(TableQuery tableQuery, Field field, Property property) {
		String type = StringUitl.toSnakeCase(field.getType().getSimpleName())
				.toUpperCase()
				.replace("STRING", "VARCHAR(255)");

		if (property != null) {
			type = property.type().isBlank() ? type : property.type();
			tableQuery.addFieldQuery(property.name(), type);
		} else {
			tableQuery.addFieldQuery(StringUitl.toSnakeCase(field.getName()), type);
		}
	}

	private static void configuraModeloDeRelacionamento(TableQuery tableQuery, Field field, Table relationShip) throws SQLException, ClassNotFoundException {
		final ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
		if (foreignKey == null) {
			throw new IllegalCallerException("Uma relação entre classes deve possuir a anotação " + ForeignKey.class.getName());
		}
		createTable(field.getType());

		final StringBuilder foreignProperties = new StringBuilder("INT");

		if (foreignKey.required()) {
			foreignProperties.append(" NOT NULL");
		}

		if (foreignKey.unique()) {
			foreignProperties.append(" UNIQUE");
		}

		String foreignColumnName = foreignKey.columnName().isBlank() ? StringUitl.toSnakeCase(field.getName()) : foreignKey.columnName();

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
