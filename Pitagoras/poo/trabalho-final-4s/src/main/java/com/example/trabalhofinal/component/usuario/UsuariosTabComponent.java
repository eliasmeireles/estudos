package com.example.trabalhofinal.component.usuario;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.model.Usuario;

public class UsuariosTabComponent extends AppTabComponent {

	private final HBox content;
	private final UsuarioFormComponent usuarioFormComponent;
	private final ListaUsuariosComponent listaUsuariosComponent;
	private final ScrollPane scrollPane;

	public UsuariosTabComponent(UsuarioTabDelegate delegate) {
		super(String.format("%s -> %s", bundle.getString("label.administracao"), bundle.getString("label.usuarios")));
		this.content = new HBox();
		this.listaUsuariosComponent = new ListaUsuariosComponent(delegate);
		this.usuarioFormComponent = new UsuarioFormComponent(delegate);
		this.scrollPane = new ScrollPane(listaUsuariosComponent);
		setRoot(content);
		configuraContent();
		reajustarView();
	}

	private void configuraContent() {
		this.usuarioFormComponent.width(215);
		this.content.setSpacing(25);
		this.content.setPadding(new Insets(16));
		this.content.getChildren().add(this.usuarioFormComponent);
		this.content.getChildren().add(scrollPane);
	}

	private void reajustarView() {
		App.mainStage.widthProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::resize));
		App.mainStage.heightProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::resize));
	}

	private void resize() {
		scrollPane.setMinWidth(App.mainStage.getWidth() - 220);
	}

	public void setUsuarios(List<Usuario> usuarios) {
		listaUsuariosComponent.setUsuarios(usuarios);
	}

	public void setUsuario(Usuario usuario) {
		usuarioFormComponent.setUsuario(usuario);
	}

	public void limparUsuarioForm() {
		usuarioFormComponent.clear();
	}

	public interface UsuarioTabDelegate extends UsuarioFormComponent.UsuarioFormDelegate {
		void onUsuarioSelecionado(Usuario usuario);
	}
}
