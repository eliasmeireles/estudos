package com.example.trabalhofinal.component;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.model.Cardapio;

public class ListaCardapioComponent extends GridPane {
	private final CardapioTabComponent.CardapioDelegate delegate;
	private List<Cardapio> cardapios;

	public ListaCardapioComponent(CardapioTabComponent.CardapioDelegate delegate) {
		this.delegate = delegate;
		this.cardapios = new ArrayList<>();
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
		reload();
	}

	public void reload() {
		getChildren().clear();

		int row = 0;
		int column = 0;
		int count = 0;
		final double cardWidth = 325;
		final long totalCards = Math.round(App.mainStage.getWidth() / (cardWidth + (cardWidth * 0.87)));
		for (Cardapio cardapio : cardapios) {
			final CardapioComponent usuarioComponent = new CardapioComponent(cardapio, delegate);
			usuarioComponent.setMinWidth(cardWidth);
			this.add(usuarioComponent, column, row);
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
