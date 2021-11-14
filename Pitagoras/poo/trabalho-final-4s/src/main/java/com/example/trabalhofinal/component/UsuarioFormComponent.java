package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.model.UsuarioPermissao;

public class UsuarioFormComponent extends VBox {

	private final UsuarioFormDelegate delegate;
	private final TextField nome;
	private final TextField login;
	private final ComboBox<UsuarioPermissao> permissaoComboBox;
	private final PasswordField senha;
	private final Button cadastar;

	public UsuarioFormComponent(UsuarioFormDelegate delegate) {
		this.delegate = delegate;
		this.nome = new TextField();
		this.login = new TextField();
		this.senha = new PasswordField();
		this.permissaoComboBox = new ComboBox<>();
		this.cadastar = new Button(bundle.getString("label.cadastrar"));
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

		VBox vBox = new VBox(cadastar);
		vBox.setAlignment(Pos.TOP_CENTER);
		getChildren().add(vBox);
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
		cadastar.setOnAction(actionEvent -> delegate.cadastrar(nome.getText(),
				login.getText(), senha.getText(), permissaoComboBox.getValue()));
	}

	private VBox label(String label, Node node) {
		VBox vBox = new VBox(new Label(label), node);
		vBox.setAlignment(Pos.TOP_LEFT);
		vBox.setSpacing(2);
		return vBox;
	}

	public void clear() {
		nome.clear();
		login.clear();
		senha.clear();
		permissaoComboBox.setValue(UsuarioPermissao.CLIENTE);
	}

	public interface UsuarioFormDelegate {
		void cadastrar(String nome, String login, String senha, UsuarioPermissao permissao);
	}
}
