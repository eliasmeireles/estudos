package com.example.trabalhofinal.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class CardComponent<T extends Pane> extends VBox {

	protected T container;

	protected CardComponent(T container) {
		this.container = container;
		setId("card-component");
		container.setId("card-container");
		container.setPadding(new Insets(16));
		setAlignment(Pos.CENTER_LEFT);
		getChildren().add(container);
		setPadding(new Insets(5));
	}
}
