package com.example.trabalhofinal.component.facoty;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.component.cardapio.MenuCardapioComponent;
import com.example.trabalhofinal.component.logout.MenuLogout;
import com.example.trabalhofinal.component.menu.MenuSair;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public class ClienteMenuBuilder implements MenuBuilder {

	@Override public List<AppMenu> getMenuOptions(TabMenuDelegate delegate) throws IOException {
		return List.of(new MenuCardapioComponent(delegate), new MenuLogout());
	}

	@Override public Pane cardapioMenu(MenuCardapio listener) {
		return new HBox(new MenuSair(listener));
	}
}
