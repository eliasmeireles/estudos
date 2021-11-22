package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.component.facoty.MenuBuilder;
import com.example.trabalhofinal.model.Mesa;

public class MesaTabComponent extends AppTabComponent<Mesa, MesaTabComponent.MesaDelegate> {

	private final MesaDelegate delegate;
	private final HBox content;
	private final MesaFormComponent formComponent;

	public MesaTabComponent(MesaDelegate delegate) {
		super(delegate, String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.mesa")));
		this.delegate = delegate;
		this.content = new HBox();
		setRoot(content);
		this.formComponent = new MesaFormComponent(delegate);
		configuraContent();
	}

	private void configuraContent() {
		this.content.setSpacing(25);
		this.formComponent.setPrefWidth(155);
		this.formComponent.withWidth(155.0);
		this.formComponent.setSpacing(8);
		this.formComponent.setAlignment(Pos.TOP_CENTER);
		this.content.setPadding(new Insets(16));
		this.content.setMinHeight(App.mainStage.getHeight() - 86);
		this.content.getChildren().add(formComponent);
		this.content.getChildren().add(scrollPane);
	}

	public void mesaDetalhes(Mesa mesa) {
		this.content.getChildren().clear();
		this.content.getChildren().add(new MesaDetalhesComponent(mesa, delegate));
	}

	public void listarMesas() {
		this.content.getChildren().clear();
		this.content.getChildren().add(formComponent);
		this.content.getChildren().add(listaComponent);
	}

	@Override protected ListaComponent<Mesa> listaComponentBuilder(MesaDelegate delegate) {
		return new ListaMesaComponent(delegate);
	}

	public interface MesaDelegate extends TabMenuDelegate<Mesa>, MenuBuilder.MenuCardapio {
	}
}
