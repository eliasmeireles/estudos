package com.example.trabalhofinal.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.trabalhofinal.App;

public class SceneUtil {
	public static final int DEFAULT_SCENE_WIDTH = 650;
	public static final int DEFAULT_SCENE_HEIGHT = 500;

	private SceneUtil() {
	}

	public static Stage stage(String xmlView) throws IOException {
		Stage stage = new Stage();
		stage(stage, xmlView);
		return stage;

	}

	public static void stage(Stage stage, String xmlView) throws IOException {
		FXMLLoader contactListView = new FXMLLoader(App.class.getResource(String.format("view/%s", xmlView)));
		Scene scene = new Scene(contactListView.load(), DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
		stage.setScene(scene);
		stage.setMinHeight(DEFAULT_SCENE_HEIGHT);
		stage.setMinWidth(DEFAULT_SCENE_WIDTH);
	}
}
