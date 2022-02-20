package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.component.facoty.MenuBuilder;
import com.example.trabalhofinal.component.pedido.PedidosTabComponent;
import com.example.trabalhofinal.model.Mesa;

public class MesaTabComponent extends AppTabComponent<Mesa, MesaTabComponent.MesaDelegate> {

	private final MesaDelegate delegate;
	private PedidosTabComponent.PedidoDelegate pedidoDelegate;
	private final HBox content;
	private final MesaFormComponent formComponent;

	public MesaTabComponent(MesaDelegate mesaDelegate, PedidosTabComponent.PedidoDelegate pedidoDelegate) {
		super(mesaDelegate, String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.mesa")));
		this.delegate = mesaDelegate;
		this.pedidoDelegate = pedidoDelegate;
		this.content = new HBox();
		setRoot(content);
		this.formComponent = new MesaFormComponent(mesaDelegate);
		configuraContent();
	}

	private void configuraContent() {
		this.content.setSpacing(25);
		this.content.setPadding(new Insets(16));
		this.content.setMinHeight(App.mainStage.getHeight() - 86);
		if (delegate.ehAdministrador()) {
			this.content.getChildren().add(formComponent);
			this.formComponent.setPrefWidth(155);
			this.formComponent.withWidth(155.0);
			this.formComponent.setSpacing(8);
			this.formComponent.setAlignment(Pos.TOP_CENTER);
		}
		this.content.getChildren().add(scrollPane);
	}

	public void mesaDetalhes(Mesa mesa) {
		this.content.getChildren().clear();
		this.content.getChildren().add(new MesaDetalhesComponent(mesa, delegate.detalhesMesaDelegate(mesa), delegate, pedidoDelegate));
	}

	public void listarMesas() {
		this.content.getChildren().clear();
		if (delegate.ehAdministrador()) {
			this.content.getChildren().add(formComponent);
		}
		this.content.getChildren().add(listaComponent);
	}

	@Override protected ListaComponent<Mesa> listaComponentBuilder(MesaDelegate delegate) {
		return new ListaMesaComponent(delegate);
	}

	public interface MesaDelegate extends TabMenuDelegate<Mesa>, MenuBuilder.MenuCardapio {
		boolean ehAdministrador();

		MesaDetalhesComponent.DetalhesMesaDelegate detalhesMesaDelegate(Mesa mesa);
	}
}
