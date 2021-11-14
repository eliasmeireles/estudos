package com.example.trabalhofinal.view.component;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CardComponent extends VBox {

	protected Pane container;

	public CardComponent(Pane container) {
		this.container = container;
		setId("card-component");
		container.setId("card-container");
		container.setPadding(new Insets(16));
		getChildren().add(container);
		setPadding(new Insets(5));
	}
}
