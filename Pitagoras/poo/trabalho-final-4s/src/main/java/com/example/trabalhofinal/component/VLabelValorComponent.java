package com.example.trabalhofinal.component;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VLabelValorComponent extends VBox {

	private final Label label;
	private final Label valor;

	public VLabelValorComponent(String label, String valor) {
		this.label = new Label(label);
		this.valor = new Label(valor);
		this.label.setId("title-label");
		setupComponent();
	}

	private void setupComponent() {
		setSpacing(3);
		getChildren().add(this.label);
		getChildren().add(this.valor);
		setWrapValue(true);
	}

	public void setValorMaximumWidth(double value) {
		valor.setMaxWidth(value);
	}

	public void setWrapValue(boolean value) {
		valor.setWrapText(value);
	}
}
