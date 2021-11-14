package com.example.trabalhofinal.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class AppTab extends Tab {

	private final Label mensagemAlert;
	private final Label removerAlert;
	private final VBox containter;
	private final HBox alertContainer;

	protected AppTab(String s) {
		super(s);
		this.mensagemAlert = new Label();
		this.removerAlert = new Label("X");
		this.containter = new VBox();
		this.alertContainer = new HBox(mensagemAlert, removerAlert);
		configurarContainer();
		configuraAlert();
	}

	private void configuraAlert() {
		mensagemAlert.setId("mensagem-alert");
		mensagemAlert.setTextFill(Color.WHITE);
		removerAlert.setId("remover-alert");
		removerAlert.setPadding(new Insets(4));
		alertContainer.setAlignment(Pos.CENTER);
		alertContainer.setPadding(new Insets(4));
		removerAlert.setOnMouseClicked(eH -> dismisAlert());
	}

	public void showErrorAlert(String mensagem) {
		mensagemAlert.setText(mensagem);
		alertContainer.setId("error-alert");
		containter.getChildren().add(0, alertContainer);
	}

	public void showSuccessAlert(String mensagem) {
		mensagemAlert.setText(mensagem);
		alertContainer.setId("success-alert");
		containter.getChildren().add(0, alertContainer);
	}

	public void dismisAlert() {
		containter.getChildren().remove(alertContainer);
	}

	private void configurarContainer() {
		containter.setAlignment(Pos.TOP_CENTER);
		containter.setSpacing(2);
		containter.setFillWidth(true);
		setContent(containter);
	}

	public final void setRoot(Node node) {
		containter.getChildren().add(node);
	}
}
