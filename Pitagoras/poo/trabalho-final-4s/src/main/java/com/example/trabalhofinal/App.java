package com.example.trabalhofinal;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.trabalhofinal.config.ResourceConfig;
import com.example.trabalhofinal.util.SceneUtil;

public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		SceneUtil.stage(stage, "login-view.fxml");
		stage.setTitle(ResourceConfig.bundle.getString("label.nome.aplicacao"));
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
