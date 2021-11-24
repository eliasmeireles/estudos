package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.SceneUtil.label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.model.Mesa;

public class MesaFormComponent extends VBox {

	private final TextField numeroMesa;
	private final ComboBox<String> comboBoxDisponibilidade;
	private final Button salvar;
	private MesaTabComponent.MesaDelegate delegate;

	public MesaFormComponent(MesaTabComponent.MesaDelegate delegate) {
		this.delegate = delegate;
		this.numeroMesa = new TextField();
		this.comboBoxDisponibilidade = new ComboBox<>();
		this.salvar = new Button(bundle.getString("label.salvar"));
		getChildren().add(label(bundle.getString("label.numero"), numeroMesa));
		getChildren().add(label(bundle.getString("label.disponibilidade"), comboBoxDisponibilidade));
		getChildren().add(salvar);
		configurarLayout();
	}

	private void configurarLayout() {
		comboBoxDisponibilidade.getItems().add(bundle.getString("label.disponivel"));
		comboBoxDisponibilidade.getItems().add(bundle.getString("label.indisponivel"));
		comboBoxDisponibilidade.setValue(bundle.getString("label.disponivel"));
		configuraNumeroMesaField();
		salvar.setOnMouseClicked(eH -> salvarMenas());
	}

	private void salvarMenas() {
		try {
			final Mesa mesa = new Mesa();
			mesa.setNumero(Integer.valueOf(numeroMesa.getText()));
			mesa.setDisponivel(comboBoxDisponibilidade.getValue().equals(bundle.getString("label.disponivel")));
			delegate.cadastrarElemento(mesa);
			numeroMesa.setText("");
			comboBoxDisponibilidade.setValue(bundle.getString("label.disponivel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void configuraNumeroMesaField() {
		numeroMesa.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d{0,5}")) {
				numeroMesa.setText(oldValue);
			}
		});
	}

	public void withWidth(double with) {
		numeroMesa.setPrefWidth(with);
		comboBoxDisponibilidade.setPrefWidth(with);
	}
}
