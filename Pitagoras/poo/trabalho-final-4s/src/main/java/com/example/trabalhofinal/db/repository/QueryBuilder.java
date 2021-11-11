package com.example.trabalhofinal.db.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.db.annotation.ForeignKey;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;
import com.example.trabalhofinal.util.StringUitl;

public class QueryBuilder {
	private final Class<?> tClass;
	private final String nomeTable;
	private final SelectQueryBuilder selectQuery;

	public QueryBuilder(Class<?> tClass, String nomeTable) {
		this.tClass = tClass;
		this.nomeTable = nomeTable;
		selectQuery = new SelectQueryBuilder();
	}

	public String build() {
		return prepararQuerySelectAll(nomeTable, tClass);
	}

	private String prepararQuerySelectAll(String nomeTable, Class<?> tClass) {
		for (Field field : tClass.getDeclaredFields()) {
			gerarQueryDeAtributo(nomeTable, field);
		}
		return selectQuery.build();
	}

	private void gerarQueryDeAtributo(String nomeTable, Field field) {
		final ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);

		if (foreignKey != null) {
			queryRelaciomanento(nomeTable, field, foreignKey);
		} else {
			final Property property = field.getAnnotation(Property.class);

			String type = StringUitl.toSnakeCase(field.getName());
			if (property != null) {
				type = property.name();
			}
			selectQuery.params.add(nomeTable + "." + type);
		}
	}

	private void queryRelaciomanento(String nomeTable, Field field, ForeignKey relationShip) {
		final Table table = field.getType().getAnnotation(Table.class);
		final String nomeColuna = relationShip.columnName().isBlank() ? table.name() + "_id" : relationShip.columnName();

		final StringBuilder join = new StringBuilder("INNER JOIN ")
				.append(table.name())
				.append(" ON ")
				.append(nomeTable)
				.append(".")
				.append(nomeColuna)
				.append(" = ")
				.append(table.name())
				.append(".")
				.append(relationShip.foreignKeyName().isBlank() ? StringUitl.toSnakeCase(table.name()) + "_id": relationShip.foreignKeyName())
				.append(" ");

		selectQuery.joins.add(join.toString());
		prepararQuerySelectAll(table.name(), field.getType());
	}

	private class SelectQueryBuilder {
		private final List<String> params = new ArrayList<>();
		private final List<String> joins = new ArrayList<>();

		private String build() {
			final StringBuilder tablesFields = new StringBuilder();
			for (String param : this.params) {
				tablesFields.append(param)
						.append(", ");
			}

			final StringBuilder fullQuery = new StringBuilder("SELECT ")
					.append(tablesFields.subSequence(0, tablesFields.length() - 2))
					.append(" FROM ")
					.append(nomeTable)
					.append(" ");

			for (String join : joins) {
				fullQuery.append(join);
			}
			return fullQuery.toString();
		}
	}
}
