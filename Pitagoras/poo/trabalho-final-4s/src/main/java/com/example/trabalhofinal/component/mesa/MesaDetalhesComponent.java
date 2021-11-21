package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.menu.MenuConcluir;
import com.example.trabalhofinal.component.menu.MenuSair;
import com.example.trabalhofinal.model.Mesa;

public class MesaDetalhesComponent extends HBox {

	private final Mesa mesa;
	private final MesaTabComponent.MesaDelegate delegate;
	private final VBox container;
	private final Label pedidosLabel;
	private final VBox mesaLayout;
	private final Button adicionarPedido;
	private final MenuSair menuSair;
	private final MenuConcluir menuConcluir;

	public MesaDetalhesComponent(Mesa mesa, MesaTabComponent.MesaDelegate delegate) {
		this.mesa = mesa;
		this.delegate = delegate;
		this.container = new VBox();
		this.mesaLayout = new VBox(new MesaComponent(mesa, delegate));
		this.pedidosLabel = new Label(bundle.getString("label.pedidos"));
		menuSair = new MenuSair(delegate);
		menuConcluir = new MenuConcluir(delegate);
		adicionarPedido = new Button(bundle.getString("label.adicionar.pedido"));

		layoutMesa();
		configurarLayout();
	}

	private void configurarLayout() {
		setSpacing(25);
		pedidosLabel.setId("title-label");
		container.setSpacing(16);
		getChildren().add(mesaLayout);
		getChildren().add(container);
		mesaLayout.setAlignment(Pos.TOP_CENTER);
		mesaLayout.setSpacing(16);
		container.getChildren().add(pedidosLabel);
		adicionarPedido.setOnMouseClicked(eH -> listarCardapios());
	}

	private void layoutMesa() {
		mesaLayout.getChildren().add(adicionarPedido);
		mesaLayout.getChildren().add(menuSair);
	}

	private void listarCardapios() {
		pedidosLabel.setText(bundle.getString("label.cardapios"));
		mesaLayout.getChildren().remove(adicionarPedido);
		mesaLayout.getChildren().remove(menuSair);
		mesaLayout.getChildren().add(menuConcluir);
	}
}
