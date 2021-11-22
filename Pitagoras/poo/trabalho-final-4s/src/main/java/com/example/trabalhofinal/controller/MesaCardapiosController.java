package com.example.trabalhofinal.controller;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.trabalhofinal.component.menu.MenuItemsComponent;
import com.example.trabalhofinal.component.mesa.MesaCardapiosComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.model.Mesa;
import com.example.trabalhofinal.model.Pedido;
import com.example.trabalhofinal.service.CardapitoService;
import com.example.trabalhofinal.service.PedidoService;

public class MesaCardapiosController implements MesaCardapiosComponent.MesaCardapioDelegate {

	private final MesaCardapiosComponent component;

	private final CardapitoService cardapitoService;
	private final PedidoService pedidoService;
	private final Mesa mesa;
	private Pedido pedido;

	private final HashMap<Integer, List<Cardapio>> cardapiosSelecionados;

	public MesaCardapiosController(Mesa mesa) {
		this.mesa = mesa;
		this.cardapiosSelecionados = new HashMap<>();
		this.cardapitoService = new CardapitoService();
		this.pedidoService = new PedidoService();
		obterPedidos();
		component = new MesaCardapiosComponent(this);
	}

	public MesaCardapiosComponent getComponent() {
		return component;
	}

	private void obterPedidos() {
		pedidoService.findPedidoAtivoDaMesa(mesa.getMesaId())
				.ifPresent(this::setPedido);
	}

	private void setPedido(Pedido pedido) {
		this.pedido = pedido;
		pedido.getCardapios().forEach(this::adcionarCardapios);
	}

	private void adcionarCardapios(Cardapio cardapio) {
		cardapiosSelecionados.computeIfAbsent(cardapio.getCardapioId(), key -> new ArrayList<>())
				.add(cardapio);
	}

	@Override public void cadastrarElemento(Cardapio elemento) {

	}

	@Override public void mostrarElemento(Cardapio elemento) {

	}

	@Override public void editarElemento(Cardapio elemento) {

	}

	@Override public void selecionarElemento(Cardapio elemento) {

	}

	@Override public boolean temPemissaoAdm() {
		return false;
	}

	@Override public void sair() {

	}

	@Override public List<Cardapio> cardapios(CardapioTipo cardapioTipo) {
		return cardapitoService.listarPorTipo(cardapioTipo);
	}

	@Override public Optional<Pane> menu(Cardapio cardapio) {
		MenuItemsComponent component = new MenuItemsComponent(totalSelecionado(cardapio));
		component.build(new ItemMenu(cardapio, component));
		return Optional.of(component);
	}

	private class ItemMenu implements MenuItemsComponent.MenuItemDelegate {

		private final Cardapio cardapio;
		private final MenuItemsComponent component;

		public ItemMenu(Cardapio cardapio, MenuItemsComponent component) {
			this.cardapio = cardapio;
			this.component = component;
		}

		@Override public void add() {
			adcionarCardapios(cardapio);
			component.atualizar(totalSelecionado(cardapio));
		}

		@Override public void remover() {
			removerCardapio(cardapio);
			component.atualizar(totalSelecionado(cardapio));
		}
	}

	private void removerCardapio(Cardapio cardapio) {
		if (cardapiosSelecionados.containsKey(cardapio.getCardapioId())) {
			cardapiosSelecionados.get(cardapio.getCardapioId()).remove(cardapio);
		}
	}

	private int totalSelecionado(Cardapio cardapio) {
		if (cardapiosSelecionados.containsKey(cardapio.getCardapioId())) {
			return cardapiosSelecionados.get(cardapio.getCardapioId()).size();
		}
		return 0;
	}

	public void concluir() {
		List<Cardapio> cardapios = new ArrayList<>();
		cardapiosSelecionados.forEach((key, value) -> cardapios.addAll(value));

		if (pedido != null) {
			pedido.setCardapios(cardapios);
		} else  {
			pedido = new Pedido();
			pedido.setCardapios(cardapios);
			pedido.setMesa(mesa);
		}
		pedido.setValorTotal(cardapios.stream().mapToDouble(Cardapio::getPreco).sum());
		pedidoService.salvar(pedido);
	}
}
