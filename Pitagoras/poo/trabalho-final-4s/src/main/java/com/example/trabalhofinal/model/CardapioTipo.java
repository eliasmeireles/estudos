package com.example.trabalhofinal.model;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.db.adapter.Adapter;

public enum CardapioTipo {

	PRATOS(bundle.getString("label.pratos")),
	CAFES(bundle.getString("label.cafe")),
	BEBIDAS(bundle.getString("label.bebidas"));

	public final String nome;

	CardapioTipo(String nome) {
		this.nome = nome;
	}

	@Override public String toString() {
		return nome;
	}

	public static CardapioTipo from(Object value) {
		try {
			for (CardapioTipo usuarioPermissao : values()) {
				if (usuarioPermissao.name().equals(value)) {
					return usuarioPermissao;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Cardápio inexistente");
	}

	public static class CardapioAdapter implements Adapter<CardapioTipo> {

		@Override public CardapioTipo toObject(Object value) {
			return CardapioTipo.from(value);
		}

		@Override public Object fromObject(Object value) {
			return ((CardapioTipo) value).name();
		}
	}
}
