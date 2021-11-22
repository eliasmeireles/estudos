package com.example.trabalhofinal.component.menu;

public class MenuAdd extends AppMenu{

	public MenuAdd(MenuActions.MenuAdd menuAdd) {
		super("add");
		setOnMouseClicked(eH -> menuAdd.add());
	}

	public MenuAdd(MenuActions.MenuAdd menuAdd, double size) {
		super("add", size);
		setOnMouseClicked(eH -> menuAdd.add());
	}
}
