package com.example.trabalhofinal.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.trabalhofinal.interator.LoginInteractor;
import com.example.trabalhofinal.util.SceneUtil;

public class LoginController implements Initializable, LoginInteractor.LoginDelegate {

	private final LoginInteractor interactor;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button login;

	@FXML
	private Label loginFailed;

	public LoginController() {
		this.interactor = new LoginInteractor(this);
	}

	@FXML protected void login() {
		interactor.login(username.getText(), password.getText());
	}

	@Override public void sucesso() {
		try {
			loginFailed.setVisible(true);
			SceneUtil.setScene("main");
		} catch (IOException e) {
			e.printStackTrace();
			falha();
		}
	}

	@Override public void falha() {
		username.setText(null);
		password.setText(null);
		loginFailed.setVisible(true);
	}

	@Override public void initialize(URL url, ResourceBundle resourceBundle) {
		username.setText("elias");
		password.setText("123456");
		Platform.runLater(() -> login.fire());
	}
}
