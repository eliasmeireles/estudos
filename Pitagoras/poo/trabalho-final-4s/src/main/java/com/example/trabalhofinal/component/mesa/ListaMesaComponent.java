package com.example.trabalhofinal.component.mesa;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Mesa;

public class ListaMesaComponent extends ListaComponent<Mesa> {

	private final MesaTabComponent.MesaDelegate delegate;

	public ListaMesaComponent(MesaTabComponent.MesaDelegate delegate) {
		super(230, 0.18);
		this.delegate = delegate;
	}

	@Override protected CardComponent<?> cardComponentBuilder(Mesa element) {
		return new MesaComponent(element, delegate);
	}
}
