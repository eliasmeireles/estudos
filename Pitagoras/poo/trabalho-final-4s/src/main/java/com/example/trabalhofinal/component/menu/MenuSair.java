package com.example.trabalhofinal.component.menu;

public class MenuSair extends AppMenu {

	public MenuSair(MenuActions.MenuSair listener) {
		super("exit");
		setOnMouseClicked(eH -> listener.sair());
	}
}
