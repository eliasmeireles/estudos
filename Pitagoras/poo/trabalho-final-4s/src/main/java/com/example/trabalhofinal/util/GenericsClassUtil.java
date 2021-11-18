package com.example.trabalhofinal.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.example.trabalhofinal.db.adapter.Adapter;
import com.example.trabalhofinal.db.annotation.OneToMany;
import com.example.trabalhofinal.db.annotation.OneToOne;
import com.example.trabalhofinal.db.annotation.PropertyAdapter;

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

	public static boolean ehAtributoSimple(Field field) {
		return field.getAnnotation(OneToOne.class) == null &&
				field.getAnnotation(OneToMany.class) == null;
	}

	public static boolean ehOneToMany(Field field) {
		return field.getAnnotation(OneToMany.class) != null;
	}

	public static boolean ehOneToOne(Field field) {
		return field.getAnnotation(OneToOne.class) != null;
	}

	public static Object obterValorDoField(Field field, Object parent) throws Exception {
		Object value;
		if (field.canAccess(parent)) {
			value = field.get(parent);
		} else {
			field.setAccessible(true);
			value = field.get(parent);
			field.setAccessible(false);
		}
		final PropertyAdapter annotation = field.getAnnotation(PropertyAdapter.class);

		if (annotation != null) {
			final Adapter<?> instance = (Adapter<?>) annotation.adapter().getConstructors()[0].newInstance();
			return instance.fromObject(value);
		}
		return value;
	}

	public static boolean ehCollection(Field fieldPk, Object valuePk, Field field) {
		return field.getAnnotation(OneToMany.class) != null && fieldPk != null && valuePk != null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T valuedAdapter(Field field, Object value) throws Exception {
		final PropertyAdapter annotation = field.getAnnotation(PropertyAdapter.class);

		if (annotation != null) {
			final Adapter<T> instance = (Adapter<T>) annotation.adapter().getConstructors()[0].newInstance();
			return instance.toObject(value);
		}
		return (T) value;
	}
}
