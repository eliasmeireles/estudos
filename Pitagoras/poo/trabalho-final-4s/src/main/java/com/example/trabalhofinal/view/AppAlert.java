package com.example.trabalhofinal.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class AppAlert extends HBox {

	private final Label mensagemAlert;
	private final Label removerAlert;
	private final OnCloseClick handle;

	public AppAlert(OnCloseClick handle) {
		this.handle = handle;
		this.mensagemAlert = new Label();
		this.removerAlert = new Label("X");
		getChildren().add(mensagemAlert);
		getChildren().add(removerAlert);
		configuraAlert();
	}

	private void configuraAlert() {
		removerAlert.setPadding(new Insets(8));
		mensagemAlert.setId("mensagem-alert");
		mensagemAlert.setTextFill(Color.WHITE);
		removerAlert.setId("remover-alert");
		setAlignment(Pos.CENTER);
		setPadding(new Insets(8));
		removerAlert.setOnMouseClicked(eH -> handle.close());
	}

	public void setSuccessMensage(String mensagem) {
		mensagemAlert.setText(mensagem);
		setId("error-alert");
	}

	public void setErrorMessage(String mensagem) {
		mensagemAlert.setText(mensagem);
		setId("success-alert");
	}

	public interface OnCloseClick {
		void close();
	}

}
