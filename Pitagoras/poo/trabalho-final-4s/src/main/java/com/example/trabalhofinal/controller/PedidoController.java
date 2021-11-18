package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.pedido.PediosTabComponent;
import com.example.trabalhofinal.service.PedidoService;

public class PedidoController implements PediosTabComponent.PedidoDelegate {

	private final PediosTabComponent tabComponent;
	private final PedidoService pedidoService;

	public PedidoController() {
		this.tabComponent = new PediosTabComponent(this);
		this.pedidoService = new PedidoService();
	}

	public PediosTabComponent getTab() {
		tabComponent.setPedidos(pedidoService.findAll());
		return tabComponent;
	}
}
