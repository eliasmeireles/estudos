package com.example.trabalhofinal.component.logout;

import static com.example.trabalhofinal.component.ViewBuilder.novoMenuItem;
import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

import java.io.IOException;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.util.ResourceUtil;

public class MenuLogout extends AppMenu {

	private final LogoutController controller;

	public MenuLogout() throws IOException {
		super(bundle.getString("label.sair"), ResourceUtil.icon("exit"));
		controller = new LogoutController();
		MenuItem sair = novoMenuItem(bundle.getString("label.sair"), "exit", 18, 20);
		getItems().add(sair);
		sair.setOnAction(eH -> controller.logout());
	}

	@Override public Tab tabInicial() {
		return controller.getTabLogout();
	}
}
