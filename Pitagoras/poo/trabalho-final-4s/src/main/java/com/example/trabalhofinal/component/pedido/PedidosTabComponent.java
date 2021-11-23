package com.example.trabalhofinal.component.pedido;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.HLabelValorComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.component.mesa.MesaCardapiosComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.model.Pedido;

public class PedidosTabComponent extends AppTabComponent<Pedido, PedidosTabComponent.PedidoDelegate> {

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

	private void configuraContent() {
		resize();
		this.content.setPadding(new Insets(16));
		this.content.getChildren().add(scrollPane);
	}

	public void mostrarDetalhesPedido(Pedido elemento) {
		final Label cardapioTitulo = new Label(
				String.format(bundle.getString("label.format.moeda.valor"), elemento.getValorTotal()));
		cardapioTitulo.setId("label-24-px");

		final Button sairDoDetalhePedido = new Button(bundle.getString("label.sair"));
		sairDoDetalhePedido.setPadding(new Insets(8));
		sairDoDetalhePedido.setOnMouseClicked(eH -> mostrarListaPedidos());
		final HBox hBox = new HBox(cardapioTitulo, sairDoDetalhePedido);
		if (!elemento.isFinalizado()) {
			hBox.getChildren().add(encerrarPedido(elemento));
		}
		hBox.setSpacing(16);
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setPadding(new Insets(0.0D, 0.0D, 16D, 0.0D));

		final MesaCardapiosComponent mesaCardapiosComponent = new MesaCardapiosComponent(new CardapioDelegate(elemento), hBox);
		scrollPane.setContent(mesaCardapiosComponent);
	}

	private Button encerrarPedido(Pedido pedido) {
		final Button button = new Button(bundle.getString("label.finalizar.pedido"));
		button.setPadding(new Insets(8));
		button.setOnMouseClicked(eH -> {
			delegate.encerrarPedido(pedido);
			mostrarListaPedidos();
			delegate.atualizarListaDepedidos();
		});
		return button;
	}

	public void mostrarListaPedidos() {
		scrollPane.setContent(listaComponent);
	}

	public interface PedidoDelegate extends TabMenuDelegate<Pedido> {
		void encerrarPedido(Pedido pedido);

		void atualizarListaDepedidos();
	}

	private class CardapioDelegate implements MesaCardapiosComponent.MesaCardapioDelegate {

		private final Pedido pedido;
		private final List<Cardapio> cardapios;

		private CardapioDelegate(Pedido pedido) {
			this.pedido = pedido;
			this.cardapios = new ArrayList<>();
			pedido.getCardapios().forEach(this::validaCardapio);
		}

		private void validaCardapio(Cardapio cardapio) {
			if (!cardapios.contains(cardapio)) {
				cardapios.add(cardapio);
			}
		}

		private long totalPorCardapio(Cardapio cardapio) {
			return pedido.getCardapios().stream().filter(c -> c.equals(cardapio)).count();
		}

		@Override public void cadastrarElemento(Cardapio elemento) {

		}

		@Override public void mostrarElemento(Cardapio elemento) {

		}

		@Override public void editarElemento(Cardapio elemento) {

		}

		@Override public void selecionarElemento(Cardapio elemento) {

		}

		@Override public boolean temPemissaoAdm() {
			return false;
		}

		@Override public void sair() {
			mostrarListaPedidos();
		}

		@Override public Optional<Pane> menu(Cardapio cardapio) {
			final long totalPorCardapio = totalPorCardapio(cardapio);
			return Optional.of(new VBox(labelQuatidadeCardapio(totalPorCardapio), labelValorPorCardapio(cardapio.getPreco() * totalPorCardapio)));
		}

		private HLabelValorComponent labelQuatidadeCardapio(long totalPorCardapio) {
			return new HLabelValorComponent(bundle.getString("label.quantidade"), String.valueOf(totalPorCardapio));
		}

		private HLabelValorComponent labelValorPorCardapio(Double valorPorCardapio) {
			return new HLabelValorComponent(bundle.getString("label.valor.total"), String.valueOf(valorPorCardapio));
		}

		@Override public List<Cardapio> cardapios(CardapioTipo cardapioTipo) {
			return cardapios;
		}
	}
}
