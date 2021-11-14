package com.example.trabalhofinal.component.facoty;

import java.io.IOException;
import java.util.List;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.component.MenuCardapioComponent;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public class ClienteMenuFactory implements MainMenuFactory {

	@Override public List<AppMenu> getMenuOptions(TabMenuDelegate delegate) throws IOException {
		return List.of(new MenuCardapioComponent(delegate));
	}
}
