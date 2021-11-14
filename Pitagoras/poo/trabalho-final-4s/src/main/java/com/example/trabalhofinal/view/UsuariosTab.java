package com.example.trabalhofinal.view;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.List;

import com.example.trabalhofinal.model.Usuario;

public class UsuariosTab extends AppTab {

	private final HBox content;
	private final UsuarioForm usuarioForm;
	private final UsuarioTabDelegate delegate;

	public UsuariosTab(UsuarioTabDelegate delegate) {
		super(String.format("%s -> %s", bundle.getString("label.administracao"), bundle.getString("label.usuarios")));
		this.delegate = delegate;
		this.content = new HBox();
		this.usuarioForm = new UsuarioForm(delegate);
		setRoot(content);
		configuraContent();
	}

	private void configuraContent() {
		this.content.setFillHeight(true);
		this.content.setPadding(new Insets(16));
		this.content.setAlignment(Pos.TOP_LEFT);
		this.content.getChildren().add(this.usuarioForm);
	}

	public void adicionarUsuarios(List<Usuario> usuarios) {

	}

	public void limparUsuarioForm() {
		usuarioForm.clear();
	}

	public interface UsuarioTabDelegate extends UsuarioForm.UsuarioFormDelegate {
		void onUsuarioSelecionado(Usuario usuario);
	}
}
