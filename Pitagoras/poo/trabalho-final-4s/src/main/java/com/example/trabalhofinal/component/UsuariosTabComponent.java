package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.model.Usuario;

public class UsuariosTabComponent extends AppTabComponent {

	private final HBox content;
	private final UsuarioFormComponent usuarioFormComponent;
	private final ListaUsuariosComponent listaUsuariosComponent;
	private final UsuarioTabDelegate delegate;

	public UsuariosTabComponent(UsuarioTabDelegate delegate) {
		super(String.format("%s -> %s", bundle.getString("label.administracao"), bundle.getString("label.usuarios")));
		this.delegate = delegate;
		this.content = new HBox();
		this.listaUsuariosComponent = new ListaUsuariosComponent();
		this.usuarioFormComponent = new UsuarioFormComponent(delegate);
		setRoot(content);
		configuraContent();
	}

	private void configuraContent() {
		this.usuarioFormComponent.width(185);
		this.listaUsuariosComponent.setPrefHeight(App.mainStage.getWidth());
		this.content.setFillHeight(true);
		this.content.setSpacing(25);
		this.content.setPadding(new Insets(18));
		this.content.setAlignment(Pos.TOP_LEFT);
		this.content.getChildren().add(this.usuarioFormComponent);

		final ScrollPane scrollPane = new ScrollPane(listaUsuariosComponent);
		scrollPane.setPrefHeight(App.mainStage.getWidth());
		scrollPane.setPrefHeight(App.mainStage.getHeight());
		scrollPane.setId("transparent-background");
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		this.content.getChildren().add(scrollPane);
	}

	public void setUsuarios(List<Usuario> usuarios) {
		listaUsuariosComponent.setUsuarios(usuarios);
	}

	public void limparUsuarioForm() {
		usuarioFormComponent.clear();
	}

	public interface UsuarioTabDelegate extends UsuarioFormComponent.UsuarioFormDelegate {
		void onUsuarioSelecionado(Usuario usuario);
	}
}
