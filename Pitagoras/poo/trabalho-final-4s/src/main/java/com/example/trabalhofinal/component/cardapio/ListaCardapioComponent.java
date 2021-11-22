package com.example.trabalhofinal.component.cardapio;

import javafx.scene.layout.Pane;

import java.util.Optional;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Cardapio;

public class ListaCardapioComponent extends ListaComponent<Cardapio> {
	private final CardapioDelegate delegate;

	public ListaCardapioComponent(CardapioDelegate delegate) {
		super(325, 0.87);
		this.delegate = delegate;
	}

	@Override protected CardComponent<?> cardComponentBuilder(Cardapio element) {
		return new CardapioComponent(element, delegate);
	}

	public interface CardapioDelegate extends CardapioFormComponent.CadapioFormDelegate {
		boolean temPemissaoAdm();

		void sair();

		default Optional<Pane> menu(Cardapio cardapio) {
			return Optional.empty();
		}
	}
}
