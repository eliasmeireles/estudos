package com.example.trabalhofinal.view;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.ResourceUtil.resource;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class MenuAdm extends Menu {

	public MenuAdm() throws IOException {
		super(bundle.getString("label.administracao"), resource.icon("setting"));
		init();
	}

	private void init() throws IOException {
		getItems().add(novoMenuItem(bundle.getString("label.usuarios"), "exit"));
	}

	private MenuItem novoMenuItem(String nome, String icone) throws IOException {
		return new MenuItem(nome, resource.icon(icone));
	}
}
