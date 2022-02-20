package com.example.trabalhofinal.component.menu;

public class MenuEditar extends AppMenu {

	public MenuEditar(MenuActions.MenuEditar listener) {
		super("edit");
		setOnMouseClicked(eH -> listener.editar());
	}
}
