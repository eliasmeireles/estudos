package com.example.trabalhofinal.component.menu;

import static com.example.trabalhofinal.component.ViewBuilder.novoMenuItem;
import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

import java.io.IOException;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.controller.MesaController;
import com.example.trabalhofinal.controller.PedidoController;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;
import com.example.trabalhofinal.util.ResourceUtil;

public class MenuServicosComponent extends AppMenu {

	private final TabMenuDelegate delegate;
	private final MesaController mesaController;
	private final PedidoController pedidoController;

	public MenuServicosComponent(TabMenuDelegate delegate) throws IOException {
		super(bundle.getString("label.servicos"), ResourceUtil.icon("support", 18, 20));
		this.delegate = delegate;
		this.mesaController = new MesaController();
		this.pedidoController = new PedidoController();
		init();
	}

	private void init() throws IOException {
		MenuItem pedidos = novoMenuItem(bundle.getString("label.pedidos"), "desk-bell", 18, 20);
		MenuItem mesa = novoMenuItem(bundle.getString("label.mesa"), "table", 18, 20);

		pedidos.setOnAction(aE -> delegate.trocarConteudo(pedidoController.getTab()));
		mesa.setOnAction(aE -> delegate.trocarConteudo(mesaController.getTab()));
		getItems().add(pedidos);
		getItems().add(mesa);
	}

	@Override public Tab tabInicial() {
		return mesaController.getTab();
	}
}
