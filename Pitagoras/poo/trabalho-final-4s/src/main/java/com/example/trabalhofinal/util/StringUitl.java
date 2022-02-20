package com.example.trabalhofinal.util;

public class StringUitl {

	private static final String regex = "([a-z])([A-Z]+)";

	private static final String replacement = "$1_$2";

	private StringUitl() {

	}

	public static String toSnakeCase(String value) {
		return value.replaceAll(regex, replacement).toLowerCase();
	}
}
