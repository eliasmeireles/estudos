package com.example.listacontatos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactListApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(ContactListApplication.class.getResource("contact-list-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 550, 500);
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
