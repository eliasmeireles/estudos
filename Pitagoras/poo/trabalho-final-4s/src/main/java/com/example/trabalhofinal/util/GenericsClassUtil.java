package com.example.trabalhofinal.util;

import static com.example.trabalhofinal.db.repository.util.QueryUtil.obterNomeDoAtributoNoBanco;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.trabalhofinal.db.adapter.Adapter;
import com.example.trabalhofinal.db.annotation.OneToMany;
import com.example.trabalhofinal.db.annotation.OneToOne;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.PropertyAdapter;

@SuppressWarnings("unchecked") public class GenericsClassUtil {

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

	@SuppressWarnings("unchecked")
	public static <T> T valuedAdapter(Field field, Object value) throws Exception {
		final PropertyAdapter annotation = field.getAnnotation(PropertyAdapter.class);

		if (annotation != null) {
			final Adapter<T> instance = (Adapter<T>) annotation.adapter().getConstructors()[0].newInstance();
			return instance.toObject(value);
		}
		return (T) value;
	}

	public static List<Field> findAllCollectionMembers(Object object) {
		return Arrays.stream(object.getClass().getDeclaredFields())
				.filter(f -> f.getAnnotation(OneToMany.class) != null)
				.collect(Collectors.toList());
	}

	public static <ID> PrimaryKeyData<ID> findObjectPk(Object object) throws Exception {
		for (Field field : object.getClass().getDeclaredFields()) {
			if (ehPk(field)) {
				return getPkData(object, field);
			}
		}
		throw new IllegalCallerException("Object não possui uma primary key");
	}

	public static boolean ehPk(Field field) {
		final Property annotation = field.getAnnotation(Property.class);
		return annotation != null && annotation.primaryKey();
	}

	public static <ID> PrimaryKeyData<ID> getPkData(Object object, Field field) throws Exception {
		return new PrimaryKeyData<>((ID) obterValorDoField(field, object), obterNomeDoAtributoNoBanco(field));
	}

	public static <T> void adicionarValorDoAtributo(T newInstance, Field field, Object value) throws Exception {
		if (field.canAccess(newInstance)) {
			field.set(newInstance, valuedAdapter(field, value));
		} else {
			field.setAccessible(true);
			field.set(newInstance, valuedAdapter(field, value));
			field.setAccessible(false);
		}
	}

	public static class PrimaryKeyData<ID> {
		private final ID pkValue;
		private final String pkName;

		public PrimaryKeyData(ID pkValue, String pkName) {
			this.pkValue = pkValue;
			this.pkName = pkName;
		}

		public ID getPkValue() {
			return pkValue;
		}

		public String getPkName() {
			return pkName;
		}
	}
}
