package com.example.trabalhofinal.component;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.App;

public abstract class AppTabComponent extends Tab {

	private final VBox containter;
	private final AppAlertComponent appAlertComponent;

	protected AppTabComponent(String s) {
		super(s);
		this.containter = new VBox();
		this.appAlertComponent = new AppAlertComponent(this::dismisAlert);
		configurarContainer();
		reajustarView();
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

	private void reajustarView() {
		App.mainStage.widthProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::resize));
		App.mainStage.heightProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::resize));
	}

	protected abstract void resize();

	public final void setRoot(Node node) {
		containter.getChildren().add(node);
	}
}
