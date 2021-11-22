package com.example.trabalhofinal.component.menu;

public class MenuConcluir extends AppMenu {

	public MenuConcluir(MenuActions.MenuConcluir listener) {
		super("check");
		setOnMouseClicked(eH -> listener.concluir());
	}
}
