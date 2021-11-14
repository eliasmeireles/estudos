package com.example.trabalhofinal.model;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.db.adapter.Adapter;

public enum UsuarioPermissao {

	ADM(bundle.getString("label.administrador")),
	GARSOM(bundle.getString("label.garcom")),
	CLIENTE(bundle.getString("label.cliente"));

	public final String nome;

	UsuarioPermissao(String nome) {
		this.nome = nome;
	}

	public static UsuarioPermissao valueOf(Object value) {
		try {
			for (UsuarioPermissao usuarioPermissao : values()) {
				if (usuarioPermissao.name().equals(value)) {
					return usuarioPermissao;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CLIENTE;
	}

	public static class UsuarioPermissaoAdapter implements Adapter<UsuarioPermissao> {

		@Override public UsuarioPermissao toObject(Object value) {
			return UsuarioPermissao.valueOf(value);
		}

		@Override public Object fromObject(Object value) {
			return value != null ? ((UsuarioPermissao) value).name() : CLIENTE.name();
		}
	}
}
