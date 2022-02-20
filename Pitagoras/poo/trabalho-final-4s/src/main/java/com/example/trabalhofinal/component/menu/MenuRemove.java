package com.example.trabalhofinal.component.menu;

public class MenuRemove extends AppMenu {
	public MenuRemove(MenuActions.MenuRemover menuRemover) {
		super("remove");
		setOnMouseClicked(eH -> menuRemover.remover());
	}
	public MenuRemove(MenuActions.MenuRemover menuRemover, double size) {
		super("remove", size);
		setOnMouseClicked(eH -> menuRemover.remover());
	}
}
