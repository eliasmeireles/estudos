package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.model.Mesa;

public class MesaTabComponent extends AppTabComponent {

	private final MesaDelegate delegate;
	private final ListaMesaComponent listaMesaComponent;
	private final ScrollPane scrollPane;
	private final HBox content;

	public MesaTabComponent(MesaDelegate delegate) {
		super(String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.mesa")));
		this.delegate = delegate;
		this.listaMesaComponent = new ListaMesaComponent(delegate);
		this.scrollPane = new ScrollPane(listaMesaComponent);
		this.content = new HBox();
		setRoot(content);
		configuraContent();
	}

	private void configuraContent() {
		this.content.setSpacing(25);
		this.content.setPadding(new Insets(16));
		this.content.getChildren().add(scrollPane);
	}

	@Override
	protected void resize() {
		scrollPane.setMinWidth(App.mainStage.getWidth() - 220);
	}

	public void setMesas(List<Mesa> mesas) {
		listaMesaComponent.setMesas(mesas);
	}

	public interface MesaDelegate {
		void mesaSelecionada(Mesa mesa);
	}
}
