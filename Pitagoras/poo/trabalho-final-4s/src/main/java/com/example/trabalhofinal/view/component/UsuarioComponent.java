package com.example.trabalhofinal.view.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.util.ResourceUtil;

public class UsuarioComponent extends HBox {

	private final Usuario usuario;
	private final VBox userData;

	public UsuarioComponent(Usuario usuario) {
		this.usuario = usuario;
		this.userData = new VBox();
		setupComponent();
		setPadding(new Insets(8));
	}

	private void setupComponent() {
		try {
			getChildren().add(ResourceUtil.resource.icon("user", 50, 50));
			setSpacing(8);
			getChildren().add(userData);
			userData.setAlignment(Pos.CENTER);
			userData.setSpacing(5);
			userData.getChildren().add(new LabelValorComponent(bundle.getString("label.nome"), usuario.getNome()));
			userData.getChildren().add(new LabelValorComponent(bundle.getString("label.login"), usuario.getLogin()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
