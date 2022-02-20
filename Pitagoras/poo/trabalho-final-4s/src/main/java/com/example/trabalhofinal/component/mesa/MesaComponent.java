package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.HLabelValorComponent;
import com.example.trabalhofinal.model.Mesa;
import com.example.trabalhofinal.util.ResourceUtil;

public class MesaComponent extends CardComponent<HBox> {

	private final Mesa mesa;
	private final VBox mesaData;

	public MesaComponent(Mesa mesa, MesaTabComponent.MesaDelegate delegate) {
		super(new HBox());
		this.mesa = mesa;
		this.mesaData = new VBox();
		setupComponent();
		setOnMouseClicked(eH -> delegate.mostrarElemento(mesa));
	}

	public MesaComponent(Mesa mesa) {
		super(new HBox());
		this.mesa = mesa;
		this.mesaData = new VBox();
		setupComponent();
	}

	private void setupComponent() {
		try {
			container.getChildren().add(ResourceUtil.icon("table", 35, 35));
			container.setAlignment(Pos.CENTER_LEFT);
			container.setSpacing(8);
			container.getChildren().add(mesaData);
			mesaData.setAlignment(Pos.CENTER_LEFT);
			mesaData.setSpacing(5);
			mesaData.getChildren().add(new HLabelValorComponent(bundle.getString("label.numero"), String.valueOf(mesa.getNumero()), 125));
			mesaData.getChildren().add(disponibilida(mesa.isDisponivel()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Label disponibilida(boolean disponivel) {
		final Label label = new Label(bundle.getString("label.disponivel"));
		label.setId("success-text");
		if (!disponivel) {
			label.setId("warning-text");
			label.setText(bundle.getString("label.indisponivel"));
		}
		return label;
	}
}
