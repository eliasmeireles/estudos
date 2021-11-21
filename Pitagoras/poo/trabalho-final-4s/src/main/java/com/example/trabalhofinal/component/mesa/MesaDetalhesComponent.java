package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.menu.MenuActions;
import com.example.trabalhofinal.component.menu.MenuConcluir;
import com.example.trabalhofinal.component.menu.MenuSair;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.model.Mesa;

public class MesaDetalhesComponent extends HBox implements MenuActions.MenuConcluir {

	private final Mesa mesa;
	private final MesaTabComponent.MesaDelegate delegate;
	private final VBox container;
	private final Label pedidosLabel;
	private final VBox mesaLayout;
	private final Button adicionarPedido;
	private final MenuSair menuSair;
	private final MenuConcluir menuConcluir;
	private final VBox cardapioContainer;

	public MesaDetalhesComponent(Mesa mesa, MesaTabComponent.MesaDelegate delegate) {
		this.mesa = mesa;
		this.delegate = delegate;
		this.container = new VBox();
		this.mesaLayout = new VBox(new MesaComponent(mesa, delegate));
		this.pedidosLabel = new Label(bundle.getString("label.pedidos"));
		this.menuSair = new MenuSair(delegate);
		this.menuConcluir = new MenuConcluir(this);
		this.cardapioContainer = new VBox();
		adicionarPedido = new Button(bundle.getString("label.adicionar.pedido"));
		configuraCardapioLayout();
		layoutMesa();
		configurarLayout();
	}

	private void configuraCardapioLayout() {
		final Label label = new Label(bundle.getString("label.cardapios"));
		label.setId("title-label");
		final ComboBox<CardapioTipo> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(CardapioTipo.values());
		comboBox.setOnAction(eh -> System.out.println(comboBox.getValue()));
		final HBox hBox = new HBox(label, comboBox);
		hBox.setSpacing(8);
		hBox.setAlignment(Pos.CENTER_LEFT);
		cardapioContainer.getChildren().add(hBox);
		comboBox.setValue(CardapioTipo.values()[0]);
	}

	private void configurarLayout() {
		setSpacing(25);
		pedidosLabel.setId("title-label");
		container.setSpacing(16);
		getChildren().add(mesaLayout);
		getChildren().add(container);
		mesaLayout.setAlignment(Pos.TOP_CENTER);
		mesaLayout.setSpacing(16);
		container.getChildren().add(pedidosLabel);
		adicionarPedido.setOnMouseClicked(eH -> listarCardapios());
	}

	private void layoutMesa() {
		mesaLayout.getChildren().add(adicionarPedido);
		mesaLayout.getChildren().add(menuSair);
	}

	private void listarCardapios() {
		container.getChildren().remove(pedidosLabel);
		mesaLayout.getChildren().remove(adicionarPedido);
		mesaLayout.getChildren().remove(menuSair);
		mesaLayout.getChildren().add(menuConcluir);
		container.getChildren().add(cardapioContainer);
	}

	@Override public void concluir() {
		pedidosLabel.setText(bundle.getString("label.pedidos"));
		mesaLayout.getChildren().remove(menuConcluir);
		mesaLayout.getChildren().add(adicionarPedido);
		mesaLayout.getChildren().add(menuSair);
	}
}
