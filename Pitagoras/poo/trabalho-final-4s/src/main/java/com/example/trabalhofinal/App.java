package com.example.trabalhofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.trabalhofinal.util.SceneUtil;

public class App extends Application {
	public static final String CONTACT_LIST_MANAGER = "System Control";

	@Override
	public void start(Stage stage) throws IOException {

		SceneUtil.stage(stage, "login-view.fxml");
		stage.setTitle(CONTACT_LIST_MANAGER);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
