package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import java.util.List;

import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;

public class CardapioTabComponent extends AppTabComponent {

	private final HBox content;
	private final CardapioTipo tipo;
	private final CardapioFormComponent cardapioFormComponent;

	public CardapioTabComponent(CardapioTabDelegate delegate, CardapioTipo tipo) {
		super(String.format("%s -> %s", bundle.getString("label.cardapio"), tipo.nome));
		this.content = new HBox();
		this.cardapioFormComponent = new CardapioFormComponent(delegate);
		setRoot(content);
		this.tipo = tipo;
		configuraContentView();
	}

	public void clear() {
		cardapioFormComponent.clear();
	}

	private void configuraContentView() {
		content.setSpacing(25);
		this.cardapioFormComponent.width(215);
		content.setPadding(new Insets(16));
		content.getChildren().add(cardapioFormComponent);
	}

	public void setCardapios(List<Cardapio> cardapios) {

	}

	public CardapioTipo getTipo() {
		return tipo;
	}

	public interface CardapioTabDelegate extends CardapioFormComponent.CadapioFormDelegate {
		void onCardapioSelecionado(Cardapio cardapio);
	}
}
