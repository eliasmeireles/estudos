package com.example.trabalhofinal.component;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public abstract class AppTabComponent extends Tab {

	private final VBox containter;
	private final AppAlertComponent appAlertComponent;

	protected AppTabComponent(String s) {
		super(s);
		this.containter = new VBox();
		this.appAlertComponent = new AppAlertComponent(this::dismisAlert);
		configurarContainer();
	}

	public void setContainerSize(double width, double height) {
		this.containter.setMinWidth(width);
		this.containter.setMinHeight(height);
	}

	public void showErrorAlert(String mensagem) {
		appAlertComponent.setErrorMessage(mensagem);
		containter.getChildren().add(0, appAlertComponent);
	}

	public void showSuccessAlert(String mensagem) {
		appAlertComponent.setSuccessMensage(mensagem);
		containter.getChildren().add(0, appAlertComponent);
	}

	public void dismisAlert() {
		containter.getChildren().remove(appAlertComponent);
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
