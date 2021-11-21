package com.example.trabalhofinal.component.pedido;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Pedido;

public class PediosTabComponent extends AppTabComponent<Pedido, PediosTabComponent.PedidoDelegate> {

	private final PedidoDelegate delegate;

	public PediosTabComponent(PedidoDelegate delegate) {
		super(delegate, String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.pedidos")));
		this.delegate = delegate;
	}

	public void setPedidos(List<Pedido> pedidos) {
		System.out.println(pedidos);
	}

	@Override protected ListaComponent<Pedido> listaComponentBuilder(PedidoDelegate delegate) {
		return new ListaComponent<>(255, 0) {
			@Override protected CardComponent<?> cardComponentBuilder(Pedido element) {
				return new CardComponent<Pane>(new VBox()) {
				};
			}
		};
	}

	public interface PedidoDelegate extends TabMenuDelegate<Pedido> {

	}
}
