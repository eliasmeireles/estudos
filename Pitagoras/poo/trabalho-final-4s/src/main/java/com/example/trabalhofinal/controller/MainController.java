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
	private final MenuAdm menuAdm;
	private final MenuCardapio menuCardapio;

	@FXML
	protected MenuBar mainMenus;

	@FXML
	protected TabPane tabPane;

	public MainController() throws IOException {
		this.menuAdm = new MenuAdm(this);
		this.menuCardapio = new MenuCardapio(this);
	}

	@Override public void initialize(URL url, ResourceBundle resourceBundle) {
		mainMenus.getMenus().add(menuAdm);
		mainMenus.getMenus().add(menuCardapio);
		trocarConteudo(menuAdm.getUsuariosController().getUsuariosTab());
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
