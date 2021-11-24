package com.example.trabalhofinal.component.pedido;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.mesa.MesaCardapiosComponent;
import com.example.trabalhofinal.model.Pedido;

public class DetalhesPedidoComponent extends VBox {

	private final PedidoCardapioDelegate.Listener listener;
	private final Pedido pedido;

	public DetalhesPedidoComponent(PedidoCardapioDelegate.Listener listener, Pedido pedido) {
		this.listener = listener;
		this.pedido = pedido;
		configurarLayoutDetalhesPedido();
	}

	public void configurarLayoutDetalhesPedido() {
		final Label cardapioTitulo = new Label(
				String.format(bundle.getString("label.format.moeda.valor"), pedido.getValorTotal()));
		cardapioTitulo.setId("label-24-px");

		final Button sairDoDetalhePedido = new Button(bundle.getString("label.sair"));
		sairDoDetalhePedido.setPadding(new Insets(8));
		sairDoDetalhePedido.setOnMouseClicked(eH -> listener.sairDetalhesPedido());
		final HBox hBox = new HBox(cardapioTitulo, sairDoDetalhePedido);
		if (!pedido.isFinalizado()) {
			hBox.getChildren().add(encerrarPedido(pedido));
		}
		hBox.setSpacing(16);
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setPadding(new Insets(0.0D, 0.0D, 16D, 0.0D));

		final MesaCardapiosComponent mesaCardapiosComponent = new MesaCardapiosComponent(new PedidoCardapioDelegate(listener, pedido), hBox);
		getChildren().add(mesaCardapiosComponent);
	}

	private Button encerrarPedido(Pedido pedido) {
		final Button button = new Button(bundle.getString("label.finalizar.pedido"));
		button.setPadding(new Insets(8));
		button.setOnMouseClicked(eH -> listener.encerrarPedito(pedido));
		return button;
	}
}
