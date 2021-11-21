package com.example.trabalhofinal.component.cardapio;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.SceneUtil.label;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.model.Cardapio;

public class CardapioFormComponent extends VBox {

	private final CadapioFormDelegate delegate;
	private final TextField nome;
	private final TextField urlImagem;
	private final TextField preco;
	private final TextArea ingredientes;
	private final Button cadastar;
	private final Button limpar;
	private Integer cardapioId;

	public CardapioFormComponent(CadapioFormDelegate delegate) {
		this.delegate = delegate;
		this.nome = new TextField();
		this.urlImagem = new TextField();
		this.preco = new TextField();
		this.ingredientes = new TextArea();
		this.cadastar = new Button(bundle.getString("label.cadastrar"));
		this.limpar = new Button(bundle.getString("label.limpar"));
		this.setPrefHeight(App.mainStage.getWidth());
		init();
		configuraPrecoField();
		configuraIngredientesField();
	}

	private void configuraIngredientesField() {

	}

	private void configuraPrecoField() {
		preco.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
				preco.setText(oldValue);
			}
		});
	}

	private void init() {
		setSpacing(8);
		getChildren().add(label(bundle.getString("label.nome"), nome));
		getChildren().add(label(bundle.getString("label.imagem.url"), urlImagem));
		getChildren().add(label(bundle.getString("label.preco"), preco));
		getChildren().add(label(bundle.getString("label.ingredientes"), ingredientes));
		ingredientes.setMaxHeight(455);
		HBox hBox = new HBox(cadastar, limpar);
		hBox.setSpacing(3);
		hBox.setAlignment(Pos.TOP_CENTER);
		getChildren().add(hBox);
		configuraBotao();
	}

	public void width(double width) {
		setMinWidth(width);
		setMaxWidth(width);
	}

	private void configuraBotao() {
		cadastar.setOnAction(actionEvent -> delegate.cadastrarElemento(build()));
		limpar.setOnAction(actionEvent -> clear());
	}

	private Cardapio build() {
		final Cardapio cardapio = new Cardapio();
		cardapio.setCardapioId(cardapioId);
		cardapio.setImagem(urlImagem.getText());
		cardapio.setNome(nome.getText());
		cardapio.setPreco(Double.parseDouble(preco.getText()));
		cardapio.setIngredientes(ingredientes.getText());
		return cardapio;
	}

	public void setCardapito(Cardapio cardapio) {
		cardapioId = cardapio.getCardapioId();
		nome.setText(cardapio.getNome());
		urlImagem.setText(cardapio.getImagem());
		preco.setText(String.valueOf(cardapio.getPreco()));
		ingredientes.setText(cardapio.getIngredientes());
	}

	public void clear() {
		nome.clear();
		ingredientes.clear();
		urlImagem.clear();
		preco.clear();
		cardapioId = null;
	}

	public interface CadapioFormDelegate extends AppTabComponent.TabMenuDelegate<Cardapio> {

	}
}
