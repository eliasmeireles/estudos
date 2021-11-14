package com.example.trabalhofinal.view;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.model.Usuario;

public class UsuariosTab extends AppTab {

	private final HBox content;
	private final UsuarioForm usuarioForm;
	private final ListaUsuarios listaUsuarios;
	private final UsuarioTabDelegate delegate;

	public UsuariosTab(UsuarioTabDelegate delegate) {
		super(String.format("%s -> %s", bundle.getString("label.administracao"), bundle.getString("label.usuarios")));
		this.delegate = delegate;
		this.content = new HBox();
		this.listaUsuarios = new ListaUsuarios();
		this.usuarioForm = new UsuarioForm(delegate);
		setRoot(content);
		configuraContent();
	}

	private void configuraContent() {
		this.usuarioForm.setMinWidth(185);
		this.content.setFillHeight(true);
		this.content.setSpacing(25);
		this.content.setPadding(new Insets(16));
		this.content.setAlignment(Pos.TOP_LEFT);
		this.content.getChildren().add(this.usuarioForm);

		final ScrollPane scrollPane = new ScrollPane(listaUsuarios);
		scrollPane.setPrefWidth(App.mainStage.getWidth());
		scrollPane.setPrefHeight(App.mainStage.getHeight());
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		this.content.getChildren().add(scrollPane);
	}

	public void setUsuarios(List<Usuario> usuarios) {
		listaUsuarios.setUsuarios(usuarios);
	}

	public void limparUsuarioForm() {
		usuarioForm.clear();
	}

	public interface UsuarioTabDelegate extends UsuarioForm.UsuarioFormDelegate {
		void onUsuarioSelecionado(Usuario usuario);
	}
}
