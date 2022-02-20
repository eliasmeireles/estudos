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

import com.example.trabalhofinal.authority.UsuarioAuthority;
import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;
import com.example.trabalhofinal.model.Usuario;

public class MainController implements Initializable, TabMenuDelegate {

	private Tab lastLoaded;

	@FXML
	protected VBox rootView;

	@FXML
	protected MenuBar mainMenus;

	@FXML
	protected TabPane tabPane;

	@Override public void initialize(URL url, ResourceBundle resourceBundle) {
		UsuarioAuthority.getUsuarioLogado().ifPresent(this::initialize);
	}

	private void initialize(Usuario usuarioLogado) {
		if (usuarioLogado != null) {
			mainMenus.getMenus().clear();
			try {
				final List<AppMenu> menus = usuarioLogado.getPermissao().menuBuilder.getMenuOptions(this);
				mainMenus.getMenus().addAll(menus);
				trocarConteudo(menus.get(0).tabInicial());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override public void trocarConteudo(Tab tabContent) {
		if (!tabContent.equals(lastLoaded)) {
			lastLoaded = tabContent;
			tabPane.setPrefHeight(rootView.getMinHeight());
			tabPane.getTabs().clear();
			tabPane.getTabs().add(tabContent);
		}
	}
}
