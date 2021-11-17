package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.ehAtributoSimple;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.db.annotation.Property;
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
		if (ehAtributoSimple(field)) {
			final Property property = field.getAnnotation(Property.class);
			String type = StringUitl.toSnakeCase(field.getName());
			if (property != null) {
				type = property.name();
			}
			selectQuery.params.add(nomeTable + "." + type);
		}
	}

	private class SelectQueryBuilder {
		private final List<String> params = new ArrayList<>();

		private String build() {
			final StringBuilder tablesFields = new StringBuilder();
			for (String param : this.params) {
				tablesFields.append(param)
						.append(", ");
			}

			return new StringBuilder("SELECT ")
					.append(tablesFields.subSequence(0, tablesFields.length() - 2))
					.append(" FROM ")
					.append(nomeTable)
					.append(" ")
					.toString();
		}
	}
}
