package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.pedido.PediosTabComponent;
import com.example.trabalhofinal.model.Mesa;
import com.example.trabalhofinal.model.Pedido;
import com.example.trabalhofinal.service.PedidoService;

public class PedidoController implements PediosTabComponent.PedidoDelegate {

	private final PediosTabComponent tabComponent;
	private final PedidoService pedidoService;

	public PedidoController() {
		this.tabComponent = new PediosTabComponent(this);
		this.pedidoService = new PedidoService();
	}

	public PediosTabComponent getTab() {
		final Mesa mesa = new Mesa();
		mesa.setMesaId(1);
		mesa.setNumero(1);
		final Pedido pedido = new Pedido();
		pedido.setPedidoId(2);
		pedido.setMesa(mesa);
		pedido.setValorTotal(355.0);
		pedido.setFinalizado(true);

		pedidoService.atualizar(pedido);

		tabComponent.setPedidos(pedidoService.findAll());
		return tabComponent;
	}
}
