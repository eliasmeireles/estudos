package com.softwareplace.systemcontrol.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import com.softwareplace.systemcontrol.domain.models.User;
import com.softwareplace.systemcontrol.domain.repository.UserRepository;
import com.softwareplace.systemcontrol.util.SceneUtil;

public class LoginController {

	private static final String FIXED_USERNAME = "admin";
	private static final String FIXED_PASSWORD = "admin";
	private UserRepository userRepository = new UserRepository();

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
		alert.setContentText("Invalid username or password.");
		alert.show();
	}

	private void showUserContactList() throws IOException {
		userRepository.create(new User(username.getText(), password.getText()));
		System.out.println(userRepository.findAll());

		//		Stage stage = SceneUtil.stage("contact-list-view.fxml");
//		stage.show();
//
//		password.getScene()
//				.getWindow()
//				.hide();
	}
}
