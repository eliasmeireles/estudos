package com.example.trabalhofinal.component.facoty;

import java.io.IOException;
import java.util.List;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public interface MainMenuFactory {

	List<AppMenu> getMenuOptions(TabMenuDelegate delegate) throws IOException;
}
