package com.example.trabalhofinal.model;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.component.facoty.AdmMenuFactory;
import com.example.trabalhofinal.component.facoty.ClienteMenuFactory;
import com.example.trabalhofinal.component.facoty.GarcomMenuFactory;
import com.example.trabalhofinal.component.facoty.MainMenuFactory;
import com.example.trabalhofinal.db.adapter.Adapter;

public enum UsuarioPermissao {

	ADM(new AdmMenuFactory(), bundle.getString("label.administrador")),
	GARCOM(new GarcomMenuFactory(), bundle.getString("label.garcom")),
	CLIENTE(new ClienteMenuFactory(), bundle.getString("label.cliente"));

	public final MainMenuFactory factory;
	public final String nome;

	UsuarioPermissao(MainMenuFactory factory, String nome) {
		this.factory = factory;
		this.nome = nome;
	}

	@Override public String toString() {
		return nome;
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
