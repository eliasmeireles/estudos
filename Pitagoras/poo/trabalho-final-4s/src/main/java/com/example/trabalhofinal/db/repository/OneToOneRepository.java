package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDoAtributoNoBanco;

import java.lang.reflect.Field;

public class OneToOneRepository extends BaseRepository<Object> {

	private final Field fromField;
	private final Object foreignKeyValue;

	public OneToOneRepository(Class<?> selectionInto, Field fromField, Object foreignKeyValue) {
		super(selectionInto);
		this.fromField = fromField;
		this.foreignKeyValue = foreignKeyValue;
	}

	public Object find() {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(obterNomeDoAtributoNoBanco(fromField))
				.append(" = ?");

		return findAll(query.toString(), foreignKeyValue).stream()
				.findFirst().orElse(null);
	}
}
