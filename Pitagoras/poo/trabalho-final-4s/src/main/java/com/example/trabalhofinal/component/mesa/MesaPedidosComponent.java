package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.pedido.PedidosTabComponent;

public class MesaPedidosComponent extends VBox {

	private final PedidosTabComponent.PedidoDelegate delegate;

	public MesaPedidosComponent(PedidosTabComponent.PedidoDelegate delegate) {
		this.delegate = delegate;
		Label pedidosLabel = new Label(bundle.getString("label.pedidos"));
		pedidosLabel.setId("title-label");
		getChildren().add(pedidosLabel);
		getChildren().add(delegate.getRootView());
	}
}
