package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.getTableName;
import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDaPk;

import java.lang.reflect.Field;

import com.example.trabalhofinal.db.annotation.OneToMany;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

public class OneToManyRepository extends BaseRepository<Object, Object> {

	private final Class<?> mainClass;
	private final String tableName;
	private final String targetPkName;
	private final String nomeDaPk;
	private final Object foreignKeyValue;

	public OneToManyRepository(Class<?> mainClass, Class<?> selectionInto,
			Field fromField, Field fieldPk, Object foreignKeyValue) {
		super(selectionInto);
		this.mainClass = mainClass;
		this.foreignKeyValue = foreignKeyValue;

		final Class<?> target = fromField
				.getAnnotation(OneToMany.class)
				.target();

		nomeDaPk = obterNomeDaPk(target);

		this.tableName = target
				.getAnnotation(Table.class)
				.name();

		this.targetPkName = fieldPk
				.getAnnotation(Property.class)
				.name();
	}

	public Object find() {
		return findAll(querBuilder(), foreignKeyValue);
	}

	private StringBuilder querBuilder() {
		final String mainClassTableName = getTableName(mainClass);
		final String collectionTableName = mainClassTableName + "_" + this.tableName + "s";
		return new StringBuilder("INNER JOIN ")
				.append(collectionTableName)
				.append(" ON ")
				.append(collectionTableName)
				.append(".")
				.append(nomeDaPk)
				.append(" = ")
				.append(tableName)
				.append(".")
				.append(nomeDaPk)
				.append(" WHERE ")
				.append(collectionTableName)
				.append(".")
				.append(targetPkName)
				.append(" = ?");
	}
}
