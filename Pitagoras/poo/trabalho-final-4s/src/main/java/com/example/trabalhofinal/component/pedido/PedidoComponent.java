package com.example.trabalhofinal.component.pedido;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.HLabelValorComponent;
import com.example.trabalhofinal.model.Pedido;
import com.example.trabalhofinal.util.ResourceUtil;

public class PedidoComponent extends CardComponent<HBox> {

	private Pedido pedido;

	public PedidoComponent(Pedido pedido, PedidosTabComponent.PedidoDelegate delegate) {
		super(new HBox());
		this.pedido = pedido;
		try {
			container.getChildren().add(ResourceUtil.icon("table", 35, 35));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!pedido.isFinalizado()) {
			container.setId("card-container-destaque");
		}

		container.setSpacing(16);
		container.setAlignment(Pos.CENTER_LEFT);
		final VBox vBox = new VBox(numeroMesa(), statusPedido(), valorPedito());
		vBox.setAlignment(Pos.CENTER_LEFT);
		vBox.setSpacing(8);
		container.getChildren().add(vBox);
		setOnMouseClicked(eH -> delegate.selecionarElemento(pedido));
	}

	private HLabelValorComponent numeroMesa() {
		return new HLabelValorComponent(bundle.getString("label.numero.pedido"), String.valueOf(pedido.getMesa().getNumero()), 125);
	}

	private HLabelValorComponent valorPedito() {
		return new HLabelValorComponent(bundle.getString("label.moeda"), String.valueOf(pedido.getValorTotal()), 125);
	}

	private HLabelValorComponent statusPedido() {
		return new HLabelValorComponent(bundle.getString("label.finalizado"), satusPedidoValor(), 125);
	}

	private String satusPedidoValor() {
		return bundle.getString(pedido.isFinalizado() ? "label.sim" : "label.nao");
	}
}
