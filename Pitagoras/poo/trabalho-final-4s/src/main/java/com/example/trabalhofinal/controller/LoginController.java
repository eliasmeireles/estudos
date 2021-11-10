package com.example.trabalhofinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;

import com.example.trabalhofinal.db.connector.DatabaseConnector;

public class LoginController {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	protected void login() {
		try {
			Connection connection = DatabaseConnector.connector.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
