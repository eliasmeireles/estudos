package com.example.trabalhofinal.component.cardapio;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.MenuSair;
import com.example.trabalhofinal.controller.CardapioController;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;

public class CardapioTabComponent extends AppTabComponent {

	private final ScrollPane scrollPane;
	private final CardapioTipo tipo;
	private final ListaCardapioComponent listaCardapioComponent;
	private HBox content;
	private CardapioFormComponent cardapioFormComponent;
	private CardapioDetalhesComponent cardapioComponent;
	private CardapioDelegate delegate;

	public CardapioTabComponent(CardapioDelegate delegate, CardapioTipo tipo) {
		super(String.format("%s -> %s", bundle.getString("label.cardapio"), tipo.nome));
		this.delegate = delegate;
		this.listaCardapioComponent = new ListaCardapioComponent(delegate);
		this.tipo = tipo;
		this.content = new HBox();
		this.scrollPane = new ScrollPane(listaCardapioComponent);
		reajustarPane();
		adicionarFormDeEdicao(delegate);
		setRoot(content);
		configuraScrollPane();
		content.setSpacing(25);
		content.setPadding(new Insets(16));
		content.getChildren().add(scrollPane);
	}

	private void adicionarFormDeEdicao(CardapioDelegate delegate) {
		if (delegate.temPemissaoAdm()) {
			this.cardapioFormComponent = new CardapioFormComponent(delegate);
			this.cardapioFormComponent.width(225);
			content.getChildren().add(cardapioFormComponent);
		}
	}

	public void clear() {
		if (cardapioFormComponent != null) {
			cardapioFormComponent.clear();
		}
	}

	private void reajustarPane() {
		App.mainStage.widthProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::configuraScrollPane));
		App.mainStage.heightProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::configuraScrollPane));
	}

	private void configuraScrollPane() {
		final int espacamento = delegate.temPemissaoAdm() ? 275 : 25;
		scrollPane.setMaxWidth(App.mainStage.getWidth() - espacamento);
		scrollPane.setMinWidth(App.mainStage.getWidth() - espacamento);
		scrollPane.setMinHeight(App.mainStage.getHeight() - 150);
	}

	public void mostrarCardapioSelecionado(Cardapio cardapio) {
		scrollPane.setContent(new CardapioDetalhesComponent(cardapio, delegate));
	}

	public void edicarCardapio(Cardapio cardapio) {
		if (cardapioFormComponent != null) {
			cardapioFormComponent.setCardapito(cardapio);
		}
	}

	public void mostrarListaCardapio() {
		scrollPane.setContent(listaCardapioComponent);
	}

	public void setCardapios(List<Cardapio> cardapios) {
		listaCardapioComponent.setCardapios(cardapios);
	}

	public CardapioTipo getTipo() {
		return tipo;
	}

	public interface CardapioDelegate extends CardapioFormComponent.CadapioFormDelegate, MenuSair.Listener {
		void mostrarCardapioSelecionado(Cardapio cardapio);

		void editarCardapio(Cardapio cardapio);

		void selecionarCardapio(Cardapio cardapio);

		boolean temPemissaoAdm();

		Pane menuBuild(CardapioController.CardapioDetalhesListener detalhesListener);
	}
}
