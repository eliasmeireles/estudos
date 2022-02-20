package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDoAtributoNoBanco;
import static com.example.trabalhofinal.util.GenericsClassUtil.obterValorDoField;

import java.lang.reflect.Field;

import com.example.trabalhofinal.db.annotation.Property;

public class OneToOneRepository extends BaseRepository<Object, Object> {

	private final Field fromField;

	public OneToOneRepository(Field object) {
		super(object.getType());
		this.fromField = object;
	}

	public Object find(Object foreignKeyValue) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(obterNomeDoAtributoNoBanco(fromField))
				.append(" = ?");

		return findAll(query, foreignKeyValue).stream()
				.findFirst().orElse(null);
	}

	@Override public Object salvar(Object objeto) throws Exception {
		for (Field field : objeto.getClass().getDeclaredFields()) {
			final Property annotation = field.getAnnotation(Property.class);
			if (annotation != null && annotation.primaryKey()) {
				final Object fieldId = obterValorDoField(field, objeto);
				if (fieldId != null) {
					return fieldId;
				}
			}
		}
		return super.salvar(objeto);
	}
}
