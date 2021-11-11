package com.example.trabalhofinal.db.repository;

import static com.example.trabalhofinal.util.GenericsClassUtil.getGenericTypeClass;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.example.trabalhofinal.db.annotation.Table;

public abstract class BaseRepository<T> {

	private final Logger logger = Logger.getAnonymousLogger();

	private final Class<T> tClass;
	private final String nomeTable;
	private final String selectAllQuery;

	BaseRepository() {
		this.tClass = getGenericTypeClass(getClass());
		new CreateTableRepository(tClass);
		nomeTable = getNomeTable();
		this.selectAllQuery = new QueryBuilder(tClass, nomeTable).build();
	}

	public List<T> findAll() {
		return Collections.emptyList();
	}

	private String getNomeTable() {
		try {
			return tClass.getAnnotation(Table.class).name();
		} catch (Exception e) {
			throw new IllegalArgumentException("Classe informada não possui a anotação de " + Table.class.getName());
		}
	}
}
