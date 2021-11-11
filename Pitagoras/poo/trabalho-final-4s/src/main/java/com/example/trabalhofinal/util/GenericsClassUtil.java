package com.example.trabalhofinal.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsClassUtil {

	private GenericsClassUtil() {

	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericTypeClass(Class<?> tclass) {
		ParameterizedType genericSuperclass = (ParameterizedType) tclass.getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
		if (type instanceof Class) {
			return (Class<T>) type;
		} else if (type instanceof ParameterizedType) {
			return (Class<T>) ((ParameterizedType) type).getRawType();
		}
		throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
	}
}
