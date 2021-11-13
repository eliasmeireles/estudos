package com.example.trabalhofinal.view;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.ResourceUtil.resource;
import static com.example.trabalhofinal.view.ViewBuilder.novoMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class MenuAdm extends Menu {

	public MenuAdm() throws IOException {
		super(bundle.getString("label.administracao"), resource.icon("setting"));
		init();
	}

	private void init() throws IOException {
		MenuItem users = novoMenuItem(bundle.getString("label.usuarios"), "users");
		getItems().add(users);
	}
}
