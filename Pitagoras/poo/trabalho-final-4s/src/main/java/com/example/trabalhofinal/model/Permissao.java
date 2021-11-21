package com.example.trabalhofinal.model;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.component.facoty.AdmMenuBuilder;
import com.example.trabalhofinal.component.facoty.ClienteMenuBuilder;
import com.example.trabalhofinal.component.facoty.GarcomMenuBuilder;
import com.example.trabalhofinal.component.facoty.MenuBuilder;
import com.example.trabalhofinal.db.adapter.Adapter;

public enum Permissao {

	ADM(new AdmMenuBuilder(), bundle.getString("label.administrador")),
	GARCOM(new GarcomMenuBuilder(), bundle.getString("label.garcom")),
	CLIENTE(new ClienteMenuBuilder(), bundle.getString("label.cliente"));

	public final MenuBuilder menuBuilder;
	public final String nome;

	Permissao(MenuBuilder menuBuilder, String nome) {
		this.menuBuilder = menuBuilder;
		this.nome = nome;
	}

	@Override public String toString() {
		return nome;
	}

	public static Permissao valueOf(Object value) {
		try {
			for (Permissao permissao : values()) {
				if (permissao.name().equals(value)) {
					return permissao;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CLIENTE;
	}

	public static class PermissaoAdapter implements Adapter<Permissao> {

		@Override public Permissao toObject(Object value) {
			return Permissao.valueOf(value);
		}

		@Override public Object fromObject(Object value) {
			return value != null ? ((Permissao) value).name() : CLIENTE.name();
		}
	}
}
