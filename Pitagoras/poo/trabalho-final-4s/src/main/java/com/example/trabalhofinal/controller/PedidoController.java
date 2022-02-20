package com.example.trabalhofinal.controller;

import javafx.scene.layout.Pane;

import java.util.List;

import com.example.trabalhofinal.component.pedido.PedidosTabComponent;
import com.example.trabalhofinal.model.Mesa;
import com.example.trabalhofinal.model.Pedido;
import com.example.trabalhofinal.service.PedidoService;

public class PedidoController implements PedidosTabComponent.PedidoDelegate {

	private Mesa mesa;
	private final PedidosTabComponent tabComponent;
	private final PedidoService pedidoService;

	public PedidoController() {
		this.mesa = null;
		this.tabComponent = new PedidosTabComponent(this);
		this.pedidoService = new PedidoService();
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public PedidosTabComponent getTab() {
		tabComponent.setElementos(getAllPedidos());
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

	@Override public void encerrarPedido(Pedido pedido) {
		pedido.setFinalizado(true);
		pedidoService.atualizar(pedido);
	}

	@Override public void atualizarListaDepedidos() {
		tabComponent.setElementos(getAllPedidos());
	}

	private List<Pedido> getAllPedidos() {
		if (mesa != null) {
			return pedidoService.findAllByMesa(mesa.getMesaId());
		}
		return pedidoService.findAll();
	}

	@Override public Pane getRootView() {
		return getTab().getRootView();
	}

	@Override public void sairDetalhesPedido() {
		tabComponent.sairDetalhesPedido();
	}
}
