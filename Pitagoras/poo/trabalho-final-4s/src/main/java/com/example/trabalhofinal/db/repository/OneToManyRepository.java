package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.getTableName;
import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDaPk;
import static com.example.trabalhofinal.util.GenericsClassUtil.findObjectPk;

import java.lang.reflect.Field;

import com.example.trabalhofinal.db.annotation.OneToMany;

public class OneToManyRepository extends BaseRepository<Object, Object> {

	private final String targetPkName;
	private final String nomeDaPk;
	private final String collectionTableName;

	public OneToManyRepository(Class<?> parent, Field selectionInto) throws Exception {
		super(selectionInto.getAnnotation(OneToMany.class)
				.target());

		this.nomeDaPk = obterNomeDaPk(parent);
		this.collectionTableName = getTableName(parent) + "_" + nomeTable + "s";
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
				.append(nomeDaPk)
				.append(" = ")
				.append(nomeTable)
				.append(".")
				.append(targetPkName)
				.append(" WHERE ")
				.append(collectionTableName)
				.append(".")
				.append(targetPkName)
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

}
