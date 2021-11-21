package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Mesa;

public class MesaTabComponent extends AppTabComponent<Mesa, MesaTabComponent.MesaDelegate> {

	private final MesaDelegate delegate;
	private final HBox content;

	public MesaTabComponent(MesaDelegate delegate) {
		super(delegate, String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.mesa")));
		this.delegate = delegate;
		this.content = new HBox();
		setRoot(content);
		configuraContent();
	}

	private void configuraContent() {
		this.content.setSpacing(25);
		this.content.setPadding(new Insets(16));
		this.content.getChildren().add(scrollPane);
	}

	@Override protected ListaComponent<Mesa> listaComponentBuilder(MesaDelegate delegate) {
		return new ListaMesaComponent(delegate);
	}

	public interface MesaDelegate extends TabMenuDelegate<Mesa> {
	}
}
