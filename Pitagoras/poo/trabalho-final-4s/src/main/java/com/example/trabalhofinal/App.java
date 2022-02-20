package com.example.trabalhofinal;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.trabalhofinal.authority.UsuarioAuthority;
import com.example.trabalhofinal.config.ResourceConfig;
import com.example.trabalhofinal.util.SceneUtil;

public class App extends Application {
	public static Stage mainStage = null;

	@Override
	public void start(Stage stage) throws IOException {
		UsuarioAuthority.init();
		SceneUtil.stage(stage, "login");
		stage.setTitle(ResourceConfig.bundle.getString("label.nome.aplicacao"));
		stage.setMaximized(true);
		stage.show();
		mainStage = stage;
	}

	public static void main(String[] args) {
		launch();
	}
}
