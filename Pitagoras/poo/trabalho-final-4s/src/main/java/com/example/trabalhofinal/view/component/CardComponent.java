package com.example.trabalhofinal.view.component;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CardComponent extends VBox {

	public CardComponent(Pane node) {
		setId("card-component");
		node.setId("card-container");
		getChildren().add(node);
		setPadding(new Insets(5));
	}
}
