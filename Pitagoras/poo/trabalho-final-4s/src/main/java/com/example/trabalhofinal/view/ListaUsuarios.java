package com.example.trabalhofinal.view;

import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.view.component.UsuarioComponent;

public class ListaUsuarios extends VBox {

	private List<Usuario> usuarios;

	public ListaUsuarios() {
		this.usuarios = new ArrayList<>();
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		reload();
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		reload();
	}

	public void reload() {
		getChildren().removeAll();
		for (Usuario usuario : usuarios) {
			getChildren().add(new UsuarioComponent(usuario));
		}
	}
}
