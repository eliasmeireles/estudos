package com.example.trabalhofinal.component.facoty;

import java.io.IOException;
import java.util.List;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.component.MenuServicosComponent;
import com.example.trabalhofinal.component.cardapio.MenuCardapioComponent;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public class GarcomMenuFactory implements MainMenuFactory {

	@Override public List<AppMenu> getMenuOptions(TabMenuDelegate delegate) throws IOException {
		return List.of(new MenuServicosComponent(delegate), new MenuCardapioComponent(delegate));
	}
}
