package com.example.trabalhofinal.util;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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
		String xmlFile = String.format("view/%s-view.fxml", xmlView);
		URL resource = App.class.getResource(xmlFile);
		if (resource != null) {
			Parent contactListView = FXMLLoader.load(resource, bundle);
			Scene scene = new Scene(contactListView, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
			stage.setScene(scene);
			stage.setMinHeight(DEFAULT_SCENE_HEIGHT);
			stage.setMinWidth(DEFAULT_SCENE_WIDTH);
		} else  {
			throw new IOException(String.format("Resource (%s) não foi encontrado", xmlFile));
		}
	}
}
