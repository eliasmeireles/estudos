package com.example.trabalhofinal.db.repository.util;

import java.lang.reflect.Field;

import com.example.trabalhofinal.db.annotation.OneToMany;
import com.example.trabalhofinal.db.annotation.OneToOne;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.util.StringUitl;

@SuppressWarnings("unchecked") public class QueryUtil {

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

	public static String gerarQueryTableCollection(Class<?> tClass, OneToMany collection) {
		final String fkParent = QueryUtil.getForeignKeyName(tClass);
		final String fkChild = QueryUtil.getForeignKeyName(collection.target());
		final String tableNameParent = QueryUtil.getTableName(tClass);
		final String tableNameChild = QueryUtil.getTableName(collection.target());

		return new StringBuilder("CREATE TABLE ")
				.append(getNomeTabelaCollection(tClass, collection.target()))
				.append("(")
				.append(fkParent)
				.append(" INT NOT NULL, ")
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

	public static String obterNomeDoAtributoNoBanco(Field field) {
		final OneToOne annotation = field.getAnnotation(OneToOne.class);
		if (annotation != null) {
			if (!annotation.foreignKeyName().isBlank()) {
				return annotation.foreignKeyName();
			}
			return StringUitl.toSnakeCase(field.getName()) + "_id";
		}
		final Property property = field.getAnnotation(Property.class);
		return property != null ? property.name() : StringUitl.toSnakeCase(field.getName());
	}

	public static String obterNomeDaPk(Class<?> tClass) {
		for (Field field : tClass.getDeclaredFields()) {
			final Property annotation = field.getAnnotation(Property.class);
			if (annotation != null && annotation.primaryKey()) {
				return annotation.name();
			}
		}

		throw new IllegalCallerException("Classe não possui um atributo com a anotação " + OneToOne.class.getName());
	}

	public static String foreingKeyQuery(String value, String tableRef, String refer) {
		return String.format("FOREIGN KEY (%s) REFERENCES %s(%s)", value, tableRef, refer);
	}

	public static String getNomeTabelaCollection(Class<?> tClass, Class<?> targetClasse) {
		return getTableName(tClass) + "_" + getTableName(targetClasse) + "s";
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
