package com.example.trabalhofinal.controller;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.component.CardapioTabComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.service.CardapitoService;

public class CardapioController implements CardapioTabComponent.CardapioDelegate {

	private final CardapitoService service;
	private final CardapioTipo tipo;
	private final CardapioTabComponent tabComponent;

	public CardapioController(CardapioTipo tipo) {
		this.service = new CardapitoService();
		this.tabComponent = new CardapioTabComponent(this, tipo);
		this.tipo = this.tabComponent.getTipo();
	}

	public CardapioTabComponent getTab() {
		tabComponent.setCardapios(service.listarPorTipo(tipo));
		return tabComponent;
	}

	@Override public void onCardapioSelecionado(Cardapio cardapio) {

	}

	@Override public void cadastrar(Cardapio cardapio) {
		cardapio.setTipo(tipo);
		if (service.salvar(cardapio)) {
			tabComponent.showSuccessAlert(bundle.getString("label.cardapio.salvo"));
			tabComponent.clear();
			tabComponent.setCardapios(service.listarPorTipo(tipo));
		} else {
			tabComponent.showSuccessAlert(bundle.getString("label.cardapio.falha"));
		}
	}

}
