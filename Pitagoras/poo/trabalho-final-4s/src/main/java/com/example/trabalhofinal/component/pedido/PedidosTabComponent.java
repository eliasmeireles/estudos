package com.example.trabalhofinal.component.pedido;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Pedido;

public class PedidosTabComponent extends AppTabComponent<Pedido, PedidosTabComponent.PedidoDelegate> implements PedidoCardapioDelegate.Listener {

	private final PedidoDelegate delegate;
	private final VBox content;

	public PedidosTabComponent(PedidoDelegate delegate) {
		super(delegate, String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.pedidos")));
		this.delegate = delegate;
		this.content = new VBox();
		setRoot(content);
		configuraContent();
	}

	@Override protected ListaComponent<Pedido> listaComponentBuilder(PedidoDelegate delegate) {
		return new ListaPedidosComponent(delegate);
	}

	public Pane getRootView() {
		return content;
	}

	private void configuraContent() {
		resize();
		this.content.setPadding(new Insets(16));
		this.content.getChildren().add(scrollPane);
	}

	public void mostrarDetalhesPedido(Pedido pedido) {
		scrollPane.setContent(new DetalhesPedidoComponent(this, pedido));
	}

	@Override public void sairDetalhesPedido() {
		scrollPane.setContent(listaComponent);
	}

	@Override public void encerrarPedito(Pedido pedido) {
		delegate.encerrarPedido(pedido);
		delegate.atualizarListaDepedidos();
		sairDetalhesPedido();
	}

	public interface PedidoDelegate extends TabMenuDelegate<Pedido> {
		void encerrarPedido(Pedido pedido);

		void atualizarListaDepedidos();

		Pane getRootView();

		void sairDetalhesPedido();
	}
}
