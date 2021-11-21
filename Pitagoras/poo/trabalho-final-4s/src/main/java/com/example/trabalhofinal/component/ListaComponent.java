package com.example.trabalhofinal.component;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.App;

public abstract class ListaComponent<T> extends GridPane {

	private final List<T> elementos;
	private final double cardSize;
	private final double ignoredSize;

	protected ListaComponent(double cardSize, double ignoredSize) {
		this.elementos = new ArrayList<>();
		this.cardSize = cardSize;
		this.ignoredSize = ignoredSize;
		reajustar();
	}

	public void setElementos(List<T> elementos) {
		this.elementos.clear();
		this.elementos.addAll(elementos);
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
		final double cardWidth = cardSize;
		long totalCards = Math.round(App.mainStage.getWidth() / cardWidth);
		while (totalCards * (cardWidth + (cardWidth * ignoredSize)) > App.mainStage.getWidth()) {
			totalCards--;
		}
		for (T elemento : elementos) {
			final CardComponent<?> cardComponent = cardComponentBuilder(elemento);
			cardComponent.setMinWidth(cardWidth);
			this.add(cardComponent, column, row);
			count++;
			column++;
			if (count >= totalCards) {
				row++;
				count = 0;
				column = 0;
			}
		}
	}

	protected abstract CardComponent<?> cardComponentBuilder(T element);
}
