package com.example.trabalhofinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;
import com.example.trabalhofinal.view.MenuAdm;
import com.example.trabalhofinal.view.MenuCardapio;

public class MainController implements Initializable, TabMenuDelegate {

	private String lastLoaded;

	@FXML
	protected MenuBar mainMenus;

	@FXML
	protected TabPane tabPane;

	@Override public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			mainMenus.getMenus().add(new MenuAdm(this));
			mainMenus.getMenus().add(new MenuCardapio(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override public void trocarConteudo(Tab tabContent) {
		String tabName = tabContent.getClass().getName();
		if (!tabName.equals(lastLoaded)) {
			lastLoaded = tabName;
			tabPane.getTabs().clear();
			tabPane.getTabs().add(tabContent);
		}
	}

}
