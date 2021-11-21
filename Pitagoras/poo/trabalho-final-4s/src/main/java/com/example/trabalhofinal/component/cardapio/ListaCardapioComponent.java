package com.example.trabalhofinal.component.cardapio;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Cardapio;

public class ListaCardapioComponent extends ListaComponent<Cardapio> {
	private final CardapioTabComponent.CardapioDelegate delegate;

	public ListaCardapioComponent(CardapioTabComponent.CardapioDelegate delegate) {
		super(325, 0.87);
		this.delegate = delegate;
	}

	@Override protected CardComponent<?> cardComponentBuilder(Cardapio element) {
		return new CardapioComponent(element, delegate);
	}
}
