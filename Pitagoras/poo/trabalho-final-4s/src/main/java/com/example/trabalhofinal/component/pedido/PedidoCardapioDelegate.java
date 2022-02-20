package com.example.trabalhofinal.component.pedido;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.trabalhofinal.component.HLabelValorComponent;
import com.example.trabalhofinal.component.mesa.MesaCardapiosComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.model.Pedido;

public class PedidoCardapioDelegate implements MesaCardapiosComponent.MesaCardapioDelegate {

	private final Listener listener;
	private final Pedido pedido;
	private final List<Cardapio> cardapios;

	public PedidoCardapioDelegate(Listener listener, Pedido pedido) {
		this.listener = listener;
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
		listener.sairDetalhesPedido();
	}

	@Override public Optional<Pane> menu(Cardapio cardapio) {
		final long totalPorCardapio = totalPorCardapio(cardapio);
		final VBox vBox = new VBox(labelQuatidadeCardapio(totalPorCardapio), labelValorPorCardapio(cardapio.getPreco() * totalPorCardapio));
		vBox.setSpacing(8);
		return Optional.of(vBox);
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

	public interface Listener {
		void sairDetalhesPedido();

		void encerrarPedito(Pedido pedido);
	}
}
