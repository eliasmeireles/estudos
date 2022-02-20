package com.example.trabalhofinal.db.adapter;

public interface Adapter<T> {

	T toObject(Object value);

	Object fromObject(Object value);
}
