package com.example.trabalhofinal.db.repository.util;

import java.lang.reflect.Field;

import com.example.trabalhofinal.db.annotation.Collection;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.util.StringUitl;

public class QueryUtil {

	private QueryUtil() {

	}

	public static String getForeignKeyName(Class<?> clazz) throws IllegalCallerException {
		for (Field field : clazz.getDeclaredFields()) {
			final Property annotation = field.getAnnotation(Property.class);
			if (annotation != null && annotation.primaryKey()) {
				return annotation.name();
			}
		}

		throw new IllegalCallerException("Classe não possui uma " + Property.class.getName() + " com a configuração de PRIMARY KEY");
	}

	public static String getTableName(Class<?> clazz) throws IllegalCallerException {
		final Table annotation = clazz.getAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}

		throw new IllegalCallerException("Classe sem a anotação " + Table.class.getName());
	}

	public static String validaQueryTabelaCollection(Class<?> tClass, Field field) {
		final Collection collection = field.getAnnotation(Collection.class);
		if (collection != null) {
			return gerarQueryTableCollection(tClass, collection);
		}
		return null;
	}

	public static SqlFieldData fieldType(Field field) {
		final Property annotation = field.getAnnotation(Property.class);

		String type = columnType(field);
		if (annotation != null) {
			type = annotation.type().isBlank() ? type : annotation.type();
			type = annotation.primaryKey() ? "INT PRIMARY KEY AUTO_INCREMENT" : type;
			return new SqlFieldData(annotation.name(), type);
		}

		return new SqlFieldData(StringUitl.toSnakeCase(field.getName()), type);
	}

	public static String columnType(Field field) {
		if (Enum.class.isAssignableFrom(field.getType())) {
			return "VARCHAR(50)";
		}
		return StringUitl.toSnakeCase(field.getType().getSimpleName())
				.toUpperCase()
				.replace("STRING", "VARCHAR(255)");
	}

	private static String gerarQueryTableCollection(Class<?> tClass, Collection collection) {
		final String fkParent = QueryUtil.getForeignKeyName(tClass);
		final String fkChild = QueryUtil.getForeignKeyName(collection.target());
		final String tableNameParent = QueryUtil.getTableName(tClass);
		final String tableNameChild = QueryUtil.getTableName(collection.target());

		return new StringBuilder("CREATE TABLE ")
				.append(tableNameParent)
				.append("_")
				.append(tableNameChild)
				.append("s(")
				.append(fkParent)
				.append(" INT PRIMARY KEY NOT NULL, ")
				.append(fkChild)
				.append(" INT NOT NULL, ")
				.append(foreingKeyQuery(fkParent, tableNameParent, fkParent))
				.append(", ")
				.append(foreingKeyQuery(fkChild, tableNameChild, fkChild))
				.append(", INDEX(")
				.append(fkParent)
				.append(", ")
				.append(fkChild)
				.append("), INDEX(")
				.append(fkParent)
				.append("))").toString();
	}

	public static String foreingKeyQuery(String value, String tableRef, String refer) {
		return String.format("FOREIGN KEY (%s) REFERENCES %s(%s)", value, tableRef, refer);
	}

	public static class SqlFieldData {
		private final String columnName;
		private final String columnType;

		public SqlFieldData(String columnName, String columnType) {
			this.columnName = columnName;
			this.columnType = columnType;
		}

		public String getColumnName() {
			return columnName;
		}

		public String getColumnType() {
			return columnType;
		}
	}
}
