package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.menu.MenuActions;
import com.example.trabalhofinal.component.menu.MenuConcluir;
import com.example.trabalhofinal.component.menu.MenuSair;
import com.example.trabalhofinal.component.pedido.PedidosTabComponent;
import com.example.trabalhofinal.model.Mesa;

public class MesaDetalhesComponent extends HBox implements MenuActions.MenuConcluir {

	private final VBox container;
	private final VBox mesaLayout;
	private final Button adicionarPedido;
	private final MenuSair menuSair;
	private final HBox menusListaCardapios;
	private final Pane cardapiosComponents;
	private final MesaPedidosComponent pedidosComponent;
	private MesaTabComponent.MesaDelegate mesaDelegate;
	private PedidosTabComponent.PedidoDelegate pedidoDelegate;
	private DetalhesMesaDelegate delegate;

	public MesaDetalhesComponent(Mesa mesa, DetalhesMesaDelegate delegate, MesaTabComponent.MesaDelegate mesaDelegate,
			PedidosTabComponent.PedidoDelegate pedidoDelegate) {
		this.delegate = delegate;
		this.mesaDelegate = mesaDelegate;
		this.pedidosComponent = new MesaPedidosComponent(pedidoDelegate);
		this.pedidoDelegate = pedidoDelegate;
		this.cardapiosComponents = delegate.getComponent(mesa);
		this.container = new VBox();
		this.mesaLayout = new VBox(new MesaComponent(mesa));
		this.menuSair = new MenuSair(this::atualizar);
		this.menusListaCardapios = new HBox(new MenuConcluir(this), new MenuSair(this::sairListaCardapios));
		adicionarPedido = new Button(bundle.getString("label.adicionar.pedido"));
		getChildren().clear();
		layoutMesa();
		configurarLayout();
	}

	private void atualizar() {
		this.pedidoDelegate.sairDetalhesPedido();
		this.mesaDelegate.sair();
	}

	private void configurarLayout() {
		setSpacing(25);
		container.setSpacing(16);
		menusListaCardapios.setAlignment(Pos.TOP_CENTER);
		getChildren().add(mesaLayout);
		getChildren().add(container);
		mesaLayout.setAlignment(Pos.TOP_CENTER);
		mesaLayout.setSpacing(16);
		mesaLayout.setMinWidth(215);
		container.getChildren().add(pedidosComponent);
		adicionarPedido.setOnMouseClicked(eH -> listarCardapios());
	}

	private void layoutMesa() {
		mesaLayout.getChildren().add(adicionarPedido);
		mesaLayout.getChildren().add(menuSair);
	}

	private void listarCardapios() {
		container.getChildren().remove(pedidosComponent);
		mesaLayout.getChildren().remove(adicionarPedido);
		mesaLayout.getChildren().remove(menuSair);
		mesaLayout.getChildren().add(menusListaCardapios);
		container.getChildren().add(cardapiosComponents);
	}

	@Override public void concluir() {
		delegate.concluir();
		sairListaCardapios();
	}

	private void sairListaCardapios() {
		this.delegate.sair();
		container.getChildren().remove(cardapiosComponents);
		mesaLayout.getChildren().remove(menusListaCardapios);
		container.getChildren().add(pedidosComponent);
		mesaLayout.getChildren().add(adicionarPedido);
		mesaLayout.getChildren().add(menuSair);
	}

	public interface DetalhesMesaDelegate {
		Pane getComponent(Mesa mesa);

		void sair();

		void concluir();
	}
}
