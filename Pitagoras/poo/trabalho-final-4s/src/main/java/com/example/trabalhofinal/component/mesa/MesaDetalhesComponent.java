package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.menu.MenuActions;
import com.example.trabalhofinal.component.menu.MenuConcluir;
import com.example.trabalhofinal.component.menu.MenuSair;
import com.example.trabalhofinal.component.pedido.PedidosTabComponent;
import com.example.trabalhofinal.controller.MesaCardapiosController;
import com.example.trabalhofinal.model.Mesa;

public class MesaDetalhesComponent extends HBox implements MenuActions.MenuConcluir {

	private final VBox container;
	private final VBox mesaLayout;
	private final Button adicionarPedido;
	private final MenuSair menuSair;
	private final HBox menusListaCardapios;
	private final MesaCardapiosController cardapiosController;
	private final MesaPedidosComponent pedidosComponent;

	public MesaDetalhesComponent(Mesa mesa, MesaTabComponent.MesaDelegate delegate, PedidosTabComponent.PedidoDelegate pedidoDelegate) {
		this.container = new VBox();
		this.pedidosComponent = new MesaPedidosComponent(mesa, pedidoDelegate);
		this.mesaLayout = new VBox(new MesaComponent(mesa));
		this.menuSair = new MenuSair(delegate);
		this.menusListaCardapios = new HBox(new MenuConcluir(this), new MenuSair(this::sairListaCardapios));
		this.cardapiosController = new MesaCardapiosController(mesa);
		adicionarPedido = new Button(bundle.getString("label.adicionar.pedido"));
		layoutMesa();
		configurarLayout();
	}

	private void configurarLayout() {
		setSpacing(25);
		container.setSpacing(16);
		menusListaCardapios.setAlignment(Pos.TOP_CENTER);
		getChildren().add(mesaLayout);
		getChildren().add(container);
		mesaLayout.setAlignment(Pos.TOP_CENTER);
		mesaLayout.setSpacing(16);
		container.getChildren().add(pedidosComponent);
		adicionarPedido.setOnMouseClicked(eH -> listarCardapios());
	}

	private void layoutMesa() {
		mesaLayout.getChildren().add(adicionarPedido);
		mesaLayout.getChildren().add(menuSair);
	}

	private void listarCardapios() {
		container.getChildren().remove(pedidosComponent);
		mesaLayout.getChildren().remove(adicionarPedido);
		mesaLayout.getChildren().remove(menuSair);
		mesaLayout.getChildren().add(menusListaCardapios);
		container.getChildren().add(cardapiosController.getComponent());
	}

	@Override public void concluir() {
		cardapiosController.concluir();
		sairListaCardapios();
	}

	private void sairListaCardapios() {
		this.cardapiosController.sair();
		container.getChildren().remove(cardapiosController.getComponent());
		container.getChildren().add(pedidosComponent);
		mesaLayout.getChildren().remove(menusListaCardapios);
		mesaLayout.getChildren().add(adicionarPedido);
		mesaLayout.getChildren().add(menuSair);
	}

}
