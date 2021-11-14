package com.example.trabalhofinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.MenuAdmComponent;
import com.example.trabalhofinal.component.MenuCardapioComponent;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public class MainController implements Initializable, TabMenuDelegate {

	private String lastLoaded;
	private final MenuAdmComponent menuAdmComponent;
	private final MenuCardapioComponent menuCardapioComponent;

	@FXML
	protected VBox rootView;

	@FXML
	protected MenuBar mainMenus;

	@FXML
	protected TabPane tabPane;

	public MainController() throws IOException {
		this.menuAdmComponent = new MenuAdmComponent(this);
		this.menuCardapioComponent = new MenuCardapioComponent(this);
	}

	@Override public void initialize(URL url, ResourceBundle resourceBundle) {
		mainMenus.getMenus().add(menuAdmComponent);
		mainMenus.getMenus().add(menuCardapioComponent);
		trocarConteudo(menuAdmComponent.getUsuariosController().getUsuariosTab());
	}

	@Override public void trocarConteudo(AppTabComponent tabContent) {
		String tabName = tabContent.getClass().getName();
		if (!tabName.equals(lastLoaded)) {
			lastLoaded = tabName;
			tabPane.setPrefHeight(rootView.getMinHeight());
			tabPane.getTabs().clear();
			tabPane.getTabs().add(tabContent);
		}
	}

}
