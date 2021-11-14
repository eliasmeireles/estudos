package com.example.trabalhofinal.component;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;

public abstract class AppMenu extends Menu {

	public AppMenu(String s, Node node) {
		super(s, node);
	}

	public abstract Tab tabInicial();
}
