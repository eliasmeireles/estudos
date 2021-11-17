package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.pedido.PediosTabComponent;

public class PedidoController implements PediosTabComponent.PedidoDelegate {

	private final PediosTabComponent tabComponent;

	public PedidoController() {
		this.tabComponent = new PediosTabComponent(this);
	}

	public PediosTabComponent getTab() {
		return tabComponent;
	}
}
