package com.example.trabalhofinal.util;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	}

	public static <T> T load(String xmlView) throws IOException {
		String xmlFile = String.format("view/%s-view.fxml", xmlView);
		URL resource = App.class.getResource(xmlFile);
		if (resource != null) {
			return FXMLLoader.load(resource, bundle);
		} else {
			throw new IOException(String.format("Resource (%s) não foi encontrado", xmlFile));
		}
	}

	public static Stage stage(String xmlView) throws IOException {
		Stage stage = new Stage();
		stage(stage, xmlView);
		return stage;
	}

	public static void stage(Stage stage, String xmlView) throws IOException {
		Parent contactListView = load(xmlView);
		Scene scene = new Scene(contactListView, DEFAULT_SCENE_WIDTH,
				DEFAULT_SCENE_HEIGHT);
		scene.setFill(Color.web("#222223"));
		stage.setScene(scene);
		stage.setMinHeight(DEFAULT_SCENE_HEIGHT);
		stage.setMinWidth(DEFAULT_SCENE_WIDTH);
	}

	public static VBox label(String label, Node node) {
		VBox vBox = new VBox(new Label(label), node);
		vBox.setAlignment(Pos.TOP_LEFT);
		vBox.setSpacing(2);
		return vBox;
	}
}
