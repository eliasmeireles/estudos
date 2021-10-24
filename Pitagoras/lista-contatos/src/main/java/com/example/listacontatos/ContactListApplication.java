package com.example.listacontatos;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.listacontatos.util.SceneUtil;

public class ContactListApplication extends Application {

	public static final String CONTACT_LIST_MANAGER = "Contact list Manager";

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
