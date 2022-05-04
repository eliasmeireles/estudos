package com.company.model;

import java.util.Arrays;

public enum Nota {
	E(4, 5),
	D(5, 7),
	C(7, 8),
	B(8, 10),
	A(10);

	private final double range;
	private final Double nextRange;

	Nota(double range) {
		this.range = range;
		nextRange = null;
	}

	Nota(double range, double nextRange) {
		this.range = range;
		this.nextRange = nextRange;
	}

	public static String getNota(double nota) {
		return E.nomeNota(nota);
	}

	private String nomeNota(double value) {
		if (value <= range) {
			return name();
		}
		return Arrays.stream(values())
				.filter(v -> nextRange != null && v.range == nextRange)
				.findFirst()
				.map(v -> v.nomeNota(value))
				.orElse(name());
	}

}
