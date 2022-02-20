package com.example.trabalhofinal.component.cardapio;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;

public class CardapioTabComponent extends AppTabComponent<Cardapio, ListaCardapioComponent.CardapioDelegate> {

	private final HBox content;
	private CardapioFormComponent cardapioFormComponent;
	private final ListaCardapioComponent.CardapioDelegate delegate;

	public CardapioTabComponent(ListaCardapioComponent.CardapioDelegate delegate, CardapioTipo tipo) {
		super(delegate, String.format("%s -> %s", bundle.getString("label.cardapios"), tipo.nome));
		this.delegate = delegate;
		this.content = new HBox();
		adicionarFormDeEdicao();
		setRoot(content);
		resize();
		content.setSpacing(25);
		content.setPadding(new Insets(16));
		content.getChildren().add(scrollPane);
	}

	private void adicionarFormDeEdicao() {
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

	@Override protected ListaComponent<Cardapio> listaComponentBuilder(ListaCardapioComponent.CardapioDelegate delegate) {
		return new ListaCardapioComponent(delegate);
	}

	@Override
	protected void resize() {
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
		scrollPane.setContent(listaComponent);
	}
}
