package com.example.listacontatos.controller;

import static com.example.listacontatos.ContactListApplication.CONTACT_LIST_MANAGER;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.listacontatos.util.SceneUtil;

public class LoginController {

	private static final String FIXED_USERNAME = "admin";
	private static final String FIXED_PASSWORD = "admin";

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	protected void login() {
		if (FIXED_USERNAME.equals(username.getText()) && FIXED_PASSWORD.equals(password.getText())) {
			try {
				showUserContactList();
			} catch (IOException e) {
				errorLogin();
			}
		} else {
			errorLogin();
		}
	}

	private void errorLogin() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Usuário ou senha inválidos");
		alert.show();
	}

	private void showUserContactList() throws IOException {
		Stage stage = SceneUtil.stage("contact-list-view.fxml");
		stage.setTitle(CONTACT_LIST_MANAGER);
		stage.show();

		password.getScene()
				.getWindow()
				.hide();
	}
}
