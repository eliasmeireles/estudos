package com.example.trabalhofinal.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public abstract class AppTab extends Tab {

	private final VBox containter;
	private final AppAlert appAlert;

	protected AppTab(String s) {
		super(s);
		this.containter = new VBox();
		this.appAlert = new AppAlert(this::dismisAlert);
		configurarContainer();
	}

	public void setContainerSize(double width, double height) {
		this.containter.setMinWidth(width);
		this.containter.setMinHeight(height);
	}

	public void showErrorAlert(String mensagem) {
		appAlert.setErrorMessage(mensagem);
		containter.getChildren().add(0, appAlert);
	}

	public void showSuccessAlert(String mensagem) {
		appAlert.setSuccessMensage(mensagem);
		containter.getChildren().add(0, appAlert);
	}

	public void dismisAlert() {
		containter.getChildren().remove(appAlert);
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
