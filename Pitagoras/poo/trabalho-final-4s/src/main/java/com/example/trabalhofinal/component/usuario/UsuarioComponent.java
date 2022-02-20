package com.example.trabalhofinal.component.usuario;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.HLabelValorComponent;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.util.ResourceUtil;

public class UsuarioComponent extends CardComponent<HBox> {

	private final Usuario usuario;
	private final VBox userData;

	public UsuarioComponent(Usuario usuario, UsuariosTabComponent.UsuarioTabDelegate delegate) {
		super(new HBox());
		this.usuario = usuario;
		this.userData = new VBox();
		setupComponent();
		setOnMouseClicked(eH -> delegate.selecionarElemento(usuario));
	}

	private void setupComponent() {
		try {
			container.getChildren().add(ResourceUtil.icon("user", 50, 50));
			container.setAlignment(Pos.CENTER_LEFT);
			container.setSpacing(8);
			container.getChildren().add(userData);
			userData.setAlignment(Pos.CENTER);
			userData.setSpacing(5);
			userData.getChildren().add(new HLabelValorComponent(bundle.getString("label.nome"), usuario.getNome(), 200));
			userData.getChildren().add(new HLabelValorComponent(bundle.getString("label.login"), usuario.getLogin()));
			userData.getChildren().add(new HLabelValorComponent(bundle.getString("label.permissao"), usuario.getPermissao().nome));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
