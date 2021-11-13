package com.example.trabalhofinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.trabalhofinal.view.MenuAdm;

public class MainController implements Initializable {

	@FXML
	protected MenuBar mainMenus;

	@FXML
	protected TabPane tabPane;

	@FXML
	public void sair() throws IOException {

	}

	@FXML
	public void usuarios() throws IOException {

	}

	@FXML
	public void cardapio() throws IOException {

	}

	@Override public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			mainMenus.getMenus().add(new MenuAdm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
