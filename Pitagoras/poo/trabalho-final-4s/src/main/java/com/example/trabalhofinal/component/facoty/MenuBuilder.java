package com.example.trabalhofinal.component.facoty;

import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.component.menu.MenuActions;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public interface MenuBuilder {

	List<AppMenu> getMenuOptions(TabMenuDelegate delegate) throws IOException;

	Pane cardapioMenu(MenuCardapio listener);

	interface MenuCardapio extends MenuActions.MenuSair, MenuActions.MenuEditar {

	}
}
