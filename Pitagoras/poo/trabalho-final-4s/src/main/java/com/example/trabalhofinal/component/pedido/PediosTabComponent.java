package com.example.trabalhofinal.component.pedido;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.component.AppTabComponent;

public class PediosTabComponent extends AppTabComponent {

	private final PedidoDelegate delegate;

	public PediosTabComponent(PedidoDelegate delegate) {
		super(String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.pedios")));
		this.delegate = delegate;
	}

	public interface PedidoDelegate {

	}
}
