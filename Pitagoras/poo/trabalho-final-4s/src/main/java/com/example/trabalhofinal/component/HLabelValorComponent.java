package com.example.trabalhofinal.component;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HLabelValorComponent extends HBox {

	private final Label label;
	private final Label valor;

	public HLabelValorComponent(String label, String valor) {
		this.label = new Label(String.format("%s:", label));
		this.valor = new Label(valor);
		this.label.setId("title-label");
		setupComponent();
	}

	public HLabelValorComponent(String label, String valor, double valorMaxWidth) {
		this.label = new Label(String.format("%s:", label));
		this.valor = new Label(valor);
		this.valor.setMaxWidth(valorMaxWidth);
		this.label.setId("title-label");
		setupComponent();
	}

	public void setValorId(String id) {
		this.valor.setId(id);
	}

	private void setupComponent() {
		setSpacing(3);
		getChildren().add(this.label);
		getChildren().add(this.valor);
	}

	public void setValorMaximumWidth(double value) {
		valor.setMaxWidth(value);
	}
}
