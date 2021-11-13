package com.example.trabalhofinal.view;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.ResourceUtil.resource;
import static com.example.trabalhofinal.view.ViewBuilder.novoMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MenuCardapio extends Menu {

	public MenuCardapio() throws IOException {
		super(bundle.getString("label.cardapio"), resource.icon("plate", 18, 20));
		init();
	}

	private void init() throws IOException {
		getItems().add(novoMenuItem(bundle.getString("label.pratos"), "plate", 18, 20));
		getItems().add(novoMenuItem(bundle.getString("label.cafe"), "coffee"));
		getItems().add(novoMenuItem(bundle.getString("label.bebidas"), "drink", 18, 20));
	}
}
