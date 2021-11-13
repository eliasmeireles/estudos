package com.example.trabalhofinal.view;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.ResourceUtil.resource;
import static com.example.trabalhofinal.view.ViewBuilder.novoMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;

import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public class MenuCardapio extends Menu {

	private final TabMenuDelegate delegate;

	public MenuCardapio(TabMenuDelegate delegate) throws IOException {
		super(bundle.getString("label.cardapio"), resource.icon("plate", 18, 20));
		this.delegate = delegate;
		init();
	}

	private void init() throws IOException {
		MenuItem plate = novoMenuItem(bundle.getString("label.pratos"), "plate", 18, 20);
		MenuItem coffee = novoMenuItem(bundle.getString("label.cafe"), "coffee");
		MenuItem drink = novoMenuItem(bundle.getString("label.bebidas"), "drink", 18, 20);

		//		plate.setOnAction(aE -> delegate.listaPratos());
		//		coffee.setOnAction(aE -> delegate.listaCafe());
		//		drink.setOnAction(aE -> delegate.listaBebidas());

		getItems().add(plate);
		getItems().add(coffee);
		getItems().add(drink);
	}
}
