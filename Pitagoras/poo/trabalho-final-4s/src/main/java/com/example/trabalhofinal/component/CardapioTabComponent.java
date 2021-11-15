package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import java.util.List;

import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;

public abstract class CardapioTabComponent extends AppTabComponent {

	private final CardapioTipo tipo;

	protected CardapioTabComponent(CardapioTipo tipo) {
		super(String.format("%s -> %s", bundle.getString("label.cardapio"), tipo.nome));
		this.tipo = tipo;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		System.out.println(cardapios);
	}

	public CardapioTipo getTipo() {
		return tipo;
	}

	public interface CardapioTabDelegate {
		void onCardapioSelecionado(Cardapio cardapio);
	}
}
