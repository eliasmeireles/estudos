package com.example.trabalhofinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import com.example.trabalhofinal.interator.LoginInteractor;
import com.example.trabalhofinal.util.SceneUtil;

public class LoginController implements LoginInteractor.LoginDelegate {

	private final LoginInteractor interactor;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

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
}
