package com.example.trabalhofinal.controller;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.authority.UsuarioAuthority;
import com.example.trabalhofinal.component.cardapio.CardapioTabComponent;
import com.example.trabalhofinal.component.cardapio.ListaCardapioComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.service.CardapitoService;

public class CardapioController implements ListaCardapioComponent.CardapioDelegate {

	private final CardapitoService service;
	private final CardapioTipo tipo;
	private final CardapioTabComponent tabComponent;

	public CardapioController(CardapioTipo tipo) {
		this.tipo = tipo;
		this.service = new CardapitoService();
		this.tabComponent = new CardapioTabComponent(this, tipo);
	}

	public CardapioTabComponent getTab() {
		tabComponent.setElementos(service.listarPorTipo(tipo));
		return tabComponent;
	}

	@Override public void cadastrarElemento(Cardapio cardapio) {
		cardapio.setTipo(tipo);
		if (service.salvar(cardapio)) {
			tabComponent.showSuccessAlert(bundle.getString("label.cardapio.salvo"));
			tabComponent.setElementos(service.listarPorTipo(tipo));
			sair();
		} else {
			tabComponent.showSuccessAlert(bundle.getString("label.cardapio.falha"));
		}
	}

	@Override public void mostrarElemento(Cardapio cardapio) {
		tabComponent.mostrarCardapioSelecionado(cardapio);
	}

	@Override public void editarElemento(Cardapio cardapio) {
		tabComponent.edicarCardapio(cardapio);
	}

	@Override public void selecionarElemento(Cardapio cardapio) {

	}

	@Override public boolean temPemissaoAdm() {
		return UsuarioAuthority.ehAdm();
	}

	@Override public void sair() {
		tabComponent.clear();
		tabComponent.mostrarListaCardapio();
	}
}
