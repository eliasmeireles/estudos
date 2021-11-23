package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import com.example.trabalhofinal.component.cardapio.ListaCardapioComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;

public class MesaCardapiosComponent extends VBox {

	private final Label pedidosLabel;
	private final ListaCardapioComponent cardapioComponent;
	private final MesaCardapioDelegate delegate;
	private Node cardapioTitulo;

	public MesaCardapiosComponent(MesaCardapioDelegate delegate) {
		this.cardapioComponent = new ListaCardapioComponent(delegate);
		this.delegate = delegate;
		this.pedidosLabel = new Label(bundle.getString("label.pedidos"));
		pedidosLabel.setId("title-label");
		configuraCardapioLayout();
	}

	public MesaCardapiosComponent(MesaCardapioDelegate delegate, Node cardapioTitulo) {
		this.cardapioTitulo = cardapioTitulo;
		this.cardapioComponent = new ListaCardapioComponent(delegate);
		this.delegate = delegate;
		this.pedidosLabel = new Label(bundle.getString("label.pedidos"));
		pedidosLabel.setId("title-label");
		configuraCardapioLayout();
	}

	private void configuraCardapioLayout() {
		final Label label = new Label(bundle.getString("label.cardapios"));
		label.setId("title-label");
		if (cardapioTitulo == null) {
			final ComboBox<CardapioTipo> comboBox = new ComboBox<>();
			comboBox.getItems().addAll(CardapioTipo.values());
			comboBox.setOnAction(eh -> recarregar(comboBox.getValue()));
			final HBox hBox = new HBox(label, comboBox);
			hBox.setSpacing(8);
			hBox.setAlignment(Pos.CENTER_LEFT);
			getChildren().add(hBox);
			comboBox.setValue(CardapioTipo.values()[0]);
		} else {
			getChildren().add(cardapioTitulo);
		}
		recarregar(CardapioTipo.values()[0]);
		getChildren().add(cardapioComponent);
	}

	private void recarregar(CardapioTipo cardapioTipo) {
		cardapioComponent.setElementos(delegate.cardapios(cardapioTipo));
	}

	public interface MesaCardapioDelegate extends ListaCardapioComponent.CardapioDelegate {
		List<Cardapio> cardapios(CardapioTipo cardapioTipo);
	}
}
