package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.pedido.PedidosTabComponent;
import com.example.trabalhofinal.model.Pedido;
import com.example.trabalhofinal.service.PedidoService;

public class PedidoController implements PedidosTabComponent.PedidoDelegate {

	private final PedidosTabComponent tabComponent;
	private final PedidoService pedidoService;

	public PedidoController() {
		this.tabComponent = new PedidosTabComponent(this);
		this.pedidoService = new PedidoService();
	}

	public PedidosTabComponent getTab() {
		tabComponent.setElementos(pedidoService.findAll());
		return tabComponent;
	}

	@Override public void cadastrarElemento(Pedido elemento) {

	}

	@Override public void mostrarElemento(Pedido elemento) {
	}

	@Override public void editarElemento(Pedido elemento) {

	}

	@Override public void selecionarElemento(Pedido elemento) {
		tabComponent.mostrarDetalhesPedido(elemento);
	}

}
