package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class UsuarioFormComponent extends VBox {

	private final UsuarioFormDelegate delegate;
	private final TextField nome;
	private final TextField login;
	private final PasswordField senha;
	private final Button cadastar;

	public UsuarioFormComponent(UsuarioFormDelegate delegate) {
		this.delegate = delegate;
		this.nome = new TextField();
		this.login = new TextField();
		this.senha = new PasswordField();
		this.cadastar = new Button(bundle.getString("label.cadastrar"));
		this.setPrefHeight(450);
		init();
	}

	private void init() {
		setSpacing(8);
		getChildren().add(label(bundle.getString("label.nome"), nome));
		getChildren().add(label(bundle.getString("label.login"), login));
		getChildren().add(label(bundle.getString("label.senha"), senha));

		VBox vBox = new VBox(cadastar);
		vBox.setAlignment(Pos.TOP_CENTER);
		getChildren().add(vBox);
		configuraBotao();
	}

	private void configuraBotao() {
		cadastar.setOnAction(actionEvent -> delegate.cadastrar(nome.getText(),
				login.getText(), senha.getText()));
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
	}

	public interface UsuarioFormDelegate {
		void cadastrar(String nome, String login, String senha);
	}
}
