package com.example.trabalhofinal.util;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import com.example.trabalhofinal.App;

public class SceneUtil {
	public static final int DEFAULT_SCENE_WIDTH = 1336;
	public static final int DEFAULT_SCENE_HEIGHT = 720;

	private SceneUtil() {
	}

	public static void setScene(String xmlView) throws IOException {
		final Stage stage = stage(xmlView);
		stage.setMaximized(true);
		App.mainStage.setScene(stage.getScene());
		App.mainStage.setMaximized(true);
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
			Scene scene = new Scene(contactListView, DEFAULT_SCENE_WIDTH,
					DEFAULT_SCENE_HEIGHT);
			scene.setFill(Color.web("#222223"));
			stage.setScene(scene);
			stage.setMinHeight(DEFAULT_SCENE_HEIGHT);
			stage.setMinWidth(DEFAULT_SCENE_WIDTH);
		} else {
			throw new IOException(String.format("Resource (%s) não foi encontrado", xmlFile));
		}
	}
}
