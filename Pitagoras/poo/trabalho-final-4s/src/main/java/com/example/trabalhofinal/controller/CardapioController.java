package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.CardapioTabComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.service.CardapitoService;

public class CardapioController implements CardapioTabComponent.CardapioTabDelegate {

	private final CardapitoService service;
	private final CardapioTipo tipo;
	private final CardapioTabComponent tabComponent;

	public CardapioController(CardapioTabComponent tabComponent) {
		this.service = new CardapitoService();
		this.tabComponent = tabComponent;
		this.tipo = tabComponent.getTipo();
	}

	public CardapioTabComponent getTab() {
		tabComponent.setCardapios(service.listarPorTipo(tipo));
		return tabComponent;
	}

	@Override public void onCardapioSelecionado(Cardapio cardapio) {

	}

	protected void listarCardapio() {

	}
}
