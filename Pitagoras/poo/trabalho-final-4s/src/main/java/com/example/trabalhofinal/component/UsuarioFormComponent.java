package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.SceneUtil.label;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.model.UsuarioPermissao;

public class UsuarioFormComponent extends VBox {

	private final UsuarioFormDelegate delegate;
	private final TextField nome;
	private final TextField login;
	private final ComboBox<UsuarioPermissao> permissaoComboBox;
	private final PasswordField senha;
	private final Button cadastar;
	private final Button limpar;
	private Integer usuarioId;

	public UsuarioFormComponent(UsuarioFormDelegate delegate) {
		this.delegate = delegate;
		this.nome = new TextField();
		this.login = new TextField();
		this.senha = new PasswordField();
		this.permissaoComboBox = new ComboBox<>();
		this.cadastar = new Button(bundle.getString("label.cadastrar"));
		this.limpar = new Button(bundle.getString("label.limpar"));
		this.setPrefHeight(App.mainStage.getWidth());
		init();
	}

	private void init() {
		setSpacing(8);
		configuraPermisaoComboBox();
		getChildren().add(label(bundle.getString("label.nome"), nome));
		getChildren().add(label(bundle.getString("label.login"), login));
		getChildren().add(label(bundle.getString("label.permissao"), permissaoComboBox));
		getChildren().add(label(bundle.getString("label.senha"), senha));

		HBox hBox = new HBox(cadastar, limpar);
		hBox.setSpacing(3);
		hBox.setAlignment(Pos.TOP_CENTER);
		getChildren().add(hBox);
		configuraBotao();
	}

	private void configuraPermisaoComboBox() {
		permissaoComboBox.setValue(UsuarioPermissao.CLIENTE);
		for (UsuarioPermissao permissao : UsuarioPermissao.values()) {
			permissaoComboBox.getItems().add(permissao);
		}
	}

	public void width(double width) {
		setPrefWidth(width);
		permissaoComboBox.setPrefWidth(width);
	}

	private void configuraBotao() {
		cadastar.setOnAction(actionEvent -> delegate.cadastrar(build()));
		limpar.setOnAction(actionEvent -> clear());
	}

	private Usuario build() {
		return new Usuario(usuarioId, nome.getText(), senha.getText(), login.getText(), permissaoComboBox.getValue());
	}

	public void setUsuario(Usuario usuario) {
		nome.setText(usuario.getNome());
		login.setText(usuario.getLogin());
		permissaoComboBox.setValue(usuario.getUsuarioPermissao());
		usuarioId = usuario.getUserId();
	}

	public void clear() {
		usuarioId = null;
		nome.clear();
		login.clear();
		senha.clear();
		permissaoComboBox.setValue(UsuarioPermissao.CLIENTE);
	}

	public interface UsuarioFormDelegate {
		void cadastrar(Usuario usuario);
	}
}
