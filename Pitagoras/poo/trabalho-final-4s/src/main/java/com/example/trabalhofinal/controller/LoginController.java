package com.example.trabalhofinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.repository.UsuarioRepository;

public class LoginController {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	protected void login() throws Exception {
		final UsuarioRepository usuarioRepository = new UsuarioRepository();
		final List<Usuario> usuarios = usuarioRepository.findAll();
		final Usuario byLoginAndPassword = usuarioRepository.findByLoginAndPassword(username.getText(), password.getText());
		byLoginAndPassword.setNome("Elias Ferreira");
		System.out.println(byLoginAndPassword);
		usuarioRepository.atualizar(byLoginAndPassword);
		//		mainStage.setScene(SceneUtil.stage("main").getScene());
		//		mainStage.setMaximized(true);
		System.out.println(usuarios);
	}
}
