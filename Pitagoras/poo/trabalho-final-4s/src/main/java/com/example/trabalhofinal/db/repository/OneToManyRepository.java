package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.getTableName;
import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDaPk;
import static com.example.trabalhofinal.util.GenericsClassUtil.findObjectPk;
import static com.example.trabalhofinal.util.GenericsClassUtil.obterValorDoField;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.db.annotation.OneToMany;

@SuppressWarnings("unchecked") public class OneToManyRepository extends BaseRepository<Object, Object> {

	private final String targetPkName;
	private final String nomeDaPk;
	private final String collectionTableName;
	private final List<Object> collectionValues;

	public OneToManyRepository(Object parent, Field selectionInto) throws Exception {
		super(selectionInto.getAnnotation(OneToMany.class)
				.target());
		collectionValues = (List<Object>) obterValorDoField(selectionInto, parent);
		this.nomeDaPk = obterNomeDaPk(parent.getClass());
		this.collectionTableName = getTableName(parent.getClass()) + "_" + nomeTable + "s";
		this.targetPkName = findObjectPk(newInstance()).getPkName();
	}

	public Object find(Object foreignKeyValue) {
		return findAll(queryBuilder(), foreignKeyValue);
	}

	private StringBuilder queryBuilder() {
		return new StringBuilder("INNER JOIN ")
				.append(collectionTableName)
				.append(" ON ")
				.append(collectionTableName)
				.append(".")
				.append(targetPkName)
				.append(" = ")
				.append(nomeTable)
				.append(".")
				.append(targetPkName)
				.append(" WHERE ")
				.append(collectionTableName)
				.append(".")
				.append(nomeDaPk)
				.append(" = ?");
	}

	public void collectionDelete(Object id) throws Exception {

		final StringBuilder query = new StringBuilder("DELETE FROM ")
				.append(collectionTableName)
				.append(" WHERE ")
				.append(collectionTableName)
				.append(".")
				.append(nomeDaPk)
				.append(" = ?");
		super.delete(query, id);
	}

	public void insertInto(Object id) throws Exception {
		if (collectionValues != null && !collectionValues.isEmpty()) {
			List<Object> values = new ArrayList<>();

			StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
					.append(collectionTableName)
					.append("(")
					.append(nomeDaPk)
					.append(", ")
					.append(targetPkName)
					.append(")")
					.append(" VALUES");

			for (Object value : collectionValues) {
				values.add(valuesSql(insertQuery, value));
			}
			String sql = insertQuery.substring(0, insertQuery.length() - 2);
			try (PreparedStatement statement = connector.getConnection().prepareStatement(sql)) {

				int cotador = 2;
				for (Object value : values) {
					statement.setObject(cotador - 1, id);
					statement.setObject(cotador, value);
					cotador += 2;
				}
				statement.execute();
			}
		}
	}

	private Object valuesSql(StringBuilder insertQuery, Object object) throws Exception {
		insertQuery.append("(?, ?), ");
		return findObjectPk(object).getPkValue();
	}

}
