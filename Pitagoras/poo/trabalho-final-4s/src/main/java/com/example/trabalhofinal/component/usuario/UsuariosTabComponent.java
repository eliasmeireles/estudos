package com.example.trabalhofinal.component.usuario;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Usuario;

public class UsuariosTabComponent extends AppTabComponent<Usuario, UsuariosTabComponent.UsuarioTabDelegate> {

	private final HBox content;
	private final UsuarioFormComponent usuarioFormComponent;

	public UsuariosTabComponent(UsuarioTabDelegate delegate) {
		super(delegate, String.format("%s -> %s", bundle.getString("label.administracao"), bundle.getString("label.usuarios")));
		this.content = new HBox();
		this.usuarioFormComponent = new UsuarioFormComponent(delegate);
		setRoot(content);
		configuraContent();
	}

	private void configuraContent() {
		this.usuarioFormComponent.width(215);
		this.content.setSpacing(25);
		this.content.setPadding(new Insets(16));
		this.content.getChildren().add(this.usuarioFormComponent);
		this.content.getChildren().add(scrollPane);
	}

	@Override protected ListaComponent<Usuario> listaComponentBuilder(UsuarioTabDelegate delegate) {
		return new ListaUsuariosComponent(delegate);
	}

	public void setUsuario(Usuario usuario) {
		usuarioFormComponent.setUsuario(usuario);
	}

	public void limparUsuarioForm() {
		usuarioFormComponent.clear();
	}

	public interface UsuarioTabDelegate extends UsuarioFormComponent.UsuarioFormDelegate {
	}
}
