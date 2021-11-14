package com.example.trabalhofinal.view.component;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LabelValorComponent extends HBox {

	private final Label label;
	private final Label valor;

	public LabelValorComponent(String label, String valor) {
		this.label = new Label(String.format("%s:", label));
		this.valor = new Label(valor);
		this.label.setId("title-label");
		setupComponent();
	}

	private void setupComponent() {
		setSpacing(3);
		getChildren().add(this.label);
		getChildren().add(this.valor);
	}
}
