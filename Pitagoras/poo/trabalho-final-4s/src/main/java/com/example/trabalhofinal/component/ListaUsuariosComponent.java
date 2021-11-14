package com.example.trabalhofinal.component;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.model.Usuario;

public class ListaUsuariosComponent extends GridPane {

	private List<Usuario> usuarios;

	public ListaUsuariosComponent() {
		this.usuarios = new ArrayList<>();
		reajustar();
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		reload();
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		reload();
	}

	private void reajustar() {
		App.mainStage.widthProperty().addListener((observableValue, number, t1) -> reload());
	}

	public void reload() {
		getChildren().clear();

		int row = 0;
		int column = 0;
		int count = 0;
		final double cardWidth = 325;
		final long totalCards = Math.round(App.mainStage.getWidth() / (cardWidth + (cardWidth * 0.87)));
		for (Usuario usuario : usuarios) {
			final CardComponent usuarioComponent = new UsuarioComponent(usuario);
			usuarioComponent.setMinWidth(cardWidth);
			this.add(usuarioComponent, column, row);
			count++;
			column++;
			if (count >= totalCards) {
				row++;
				count = 0;
				column = 0;
			}
		}
	}
}
