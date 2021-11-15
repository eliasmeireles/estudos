package com.example.trabalhofinal.controller;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import com.example.trabalhofinal.component.MenuEditar;
import com.example.trabalhofinal.component.MenuSair;
import com.example.trabalhofinal.component.MenuSelecionar;
import com.example.trabalhofinal.component.cardapio.CardapioTabComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.model.UsuarioPermissao;
import com.example.trabalhofinal.service.CardapitoService;
import com.example.trabalhofinal.service.UsuarioService;

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

	@Override public void mostrarCardapioSelecionado(Cardapio cardapio) {
		tabComponent.mostrarCardapioSelecionado(cardapio);
	}

	@Override public void editarCardapio(Cardapio cardapio) {
		tabComponent.edicarCardapio(cardapio);
	}

	@Override public void selecionarCardapio(Cardapio cardapio) {

	}

	@Override public Pane menuBuild(CardapioDetalhesListener detalhesListener) {
		final Usuario usuarioLogado = UsuarioService.getInstance().getUsuarioLogado();
		if (UsuarioPermissao.CLIENTE.equals(usuarioLogado.getUsuarioPermissao())) {
			return new HBox(new MenuSair(this));
		} else if (UsuarioPermissao.GARCOM.equals(usuarioLogado.getUsuarioPermissao())) {
			return new HBox(new MenuSair(this), new MenuSelecionar(detalhesListener));
		} else if (UsuarioPermissao.ADM.equals(usuarioLogado.getUsuarioPermissao())) {
			return new HBox(new MenuSair(this), new MenuEditar(detalhesListener), new MenuSelecionar(detalhesListener));
		}
		return new Pane();
	}

	@Override public boolean temPemissaoAdm() {
		return UsuarioPermissao.ADM.equals(UsuarioService.getInstance().getUsuarioLogado().getUsuarioPermissao());
	}

	@Override public void cadastrar(Cardapio cardapio) {
		cardapio.setTipo(tipo);
		if (service.salvar(cardapio)) {
			tabComponent.showSuccessAlert(bundle.getString("label.cardapio.salvo"));
			tabComponent.setCardapios(service.listarPorTipo(tipo));
			sair();
		} else {
			tabComponent.showSuccessAlert(bundle.getString("label.cardapio.falha"));
		}
	}

	@Override public void sair() {
		tabComponent.clear();
		tabComponent.mostrarListaCardapio();
	}

	public interface CardapioDetalhesListener extends MenuEditar.Listener, MenuSelecionar.Listener {
	}
}
