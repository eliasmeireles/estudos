package com.example.trabalhofinal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.service.UsuarioService;

public class MainController implements Initializable, TabMenuDelegate {

	private String lastLoaded;
	private final UsuarioService service;

	@FXML
	protected VBox rootView;

	@FXML
	protected MenuBar mainMenus;

	@FXML
	protected TabPane tabPane;

	public MainController() {
		this.service = UsuarioService.getInstance();
	}

	@Override public void initialize(URL url, ResourceBundle resourceBundle) {
		final Usuario usuarioLogado = service.getUsuarioLogado();
		if (usuarioLogado != null) {
			mainMenus.getMenus().clear();
			try {
				final List<AppMenu> menus = usuarioLogado.getUsuarioPermissao().factory.getMenuOptions(this);
				mainMenus.getMenus().addAll(menus);
				trocarConteudo(menus.get(0).tabInicial());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override public void trocarConteudo(Tab tabContent) {
		String tabName = tabContent.getClass().getName();
		if (!tabName.equals(lastLoaded)) {
			lastLoaded = tabName;
			tabPane.setPrefHeight(rootView.getMinHeight());
			tabPane.getTabs().clear();
			tabPane.getTabs().add(tabContent);
		}
	}
}
