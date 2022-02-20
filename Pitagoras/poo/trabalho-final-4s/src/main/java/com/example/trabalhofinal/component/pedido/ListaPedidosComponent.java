package com.example.trabalhofinal.component.pedido;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Pedido;

public class ListaPedidosComponent extends ListaComponent<Pedido> {

	private PedidosTabComponent.PedidoDelegate delegate;

	protected ListaPedidosComponent(PedidosTabComponent.PedidoDelegate delegate) {
		super(255, 0.05);
		this.delegate = delegate;
	}

	@Override protected CardComponent<?> cardComponentBuilder(Pedido pedido) {
		return new PedidoComponent(pedido, delegate);
	}

}
