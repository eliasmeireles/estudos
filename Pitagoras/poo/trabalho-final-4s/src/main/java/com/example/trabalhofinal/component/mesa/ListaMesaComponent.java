package com.example.trabalhofinal.component.mesa;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.model.Mesa;

public class ListaMesaComponent extends GridPane {

	private final MesaTabComponent.MesaDelegate delegate;
	private List<Mesa> mesas;

	public ListaMesaComponent(MesaTabComponent.MesaDelegate delegate) {
		this.delegate = delegate;
		this.mesas = new ArrayList<>();
		reajustar();
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
		reload();
	}

	private void reajustar() {
		App.mainStage.widthProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::reload));
		App.mainStage.heightProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::reload));
	}

	public void reload() {
		getChildren().clear();
		setAlignment(Pos.TOP_RIGHT);
		int row = 0;
		int column = 0;
		int count = 0;
		final double cardWidth = 335;
		long totalCards = Math.round(App.mainStage.getWidth() / cardWidth);
		while (totalCards * (cardWidth + (cardWidth * 0.2)) > App.mainStage.getWidth()) {
			totalCards--;
		}
		for (Mesa mesa : mesas) {
			final CardComponent mesaComponent = new MesaComponent(mesa, delegate);
			mesaComponent.setMinWidth(cardWidth);
			this.add(mesaComponent, column, row);
			count++;
			column++;
			if (count >= totalCards) {
				row++;
				count = 0;
				column = 0;
			}
		}
	}
}
