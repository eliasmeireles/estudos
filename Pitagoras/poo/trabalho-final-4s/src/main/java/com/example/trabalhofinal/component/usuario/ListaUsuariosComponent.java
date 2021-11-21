package com.example.trabalhofinal.component.usuario;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.ListaComponent;
import com.example.trabalhofinal.model.Usuario;

public class ListaUsuariosComponent extends ListaComponent<Usuario> {

	private final UsuariosTabComponent.UsuarioTabDelegate delegate;

	public ListaUsuariosComponent(UsuariosTabComponent.UsuarioTabDelegate delegate) {
		super(335, 0.2);
		this.delegate = delegate;
	}

	@Override protected CardComponent<?> cardComponentBuilder(Usuario element) {
		return new UsuarioComponent(element, delegate);
	}
}
