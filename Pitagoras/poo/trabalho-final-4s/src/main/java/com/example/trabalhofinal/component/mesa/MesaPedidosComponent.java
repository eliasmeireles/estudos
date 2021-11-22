package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.model.Mesa;

public class MesaPedidosComponent extends VBox {

	private final Mesa mesa;

	public MesaPedidosComponent(Mesa mesa) {
		this.mesa = mesa;
		Label pedidosLabel = new Label(bundle.getString("label.pedidos"));
		pedidosLabel.setId("title-label");
		getChildren().add(pedidosLabel);
	}
}
