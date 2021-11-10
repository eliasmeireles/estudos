package com.example.trabalhofinal.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUitlTest {

	@Test
	void deve_ConverterUmaStringCamelCase_ParaSnakeCase() {
		String valorDeEntrada = "camelCase";
		String valorDeSaidaEsperado = "camel_case";

		final String valorDeSaida = StringUitl.toSnakeCase(valorDeEntrada);
		assertEquals(valorDeSaidaEsperado, valorDeSaida);
	}
}
