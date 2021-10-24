package com.example.listacontatos.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.listacontatos.ContactListApplication;

public class LoginController {

	private static final String USERNAME = "elias";
	private static final String PASSWORD = "password";

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	protected void login() {
		if (USERNAME.equals(username.getText()) && PASSWORD.equals(password.getText())) {
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
		Stage stage = new Stage();
		FXMLLoader contactListView = new FXMLLoader(ContactListApplication.class.getResource("contact-list-view.fxml"));
		Scene scene = new Scene(contactListView.load(), 500, 350);
		stage.setTitle("Contact List");
		stage.setScene(scene);
		stage.show();

		password.getScene()
				.getWindow()
				.hide();
	}
}
