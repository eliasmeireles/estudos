package com.example.trabalhofinal.component.facoty;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.component.cardapio.MenuCardapioComponent;
import com.example.trabalhofinal.component.menu.MenuAdmComponent;
import com.example.trabalhofinal.component.menu.MenuServicosComponent;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public class AdmMenuFactory implements MainMenuFactory {

	@Override public List<AppMenu> getMenuOptions(TabMenuDelegate delegate) throws IOException {
		return Arrays.asList(new MenuAdmComponent(delegate), new MenuServicosComponent(delegate), new MenuCardapioComponent(delegate));
	}
}
