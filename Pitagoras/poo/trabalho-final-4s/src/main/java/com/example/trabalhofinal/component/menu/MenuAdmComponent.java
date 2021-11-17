package com.example.trabalhofinal.component.menu;

import static com.example.trabalhofinal.component.ViewBuilder.novoMenuItem;
import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

import java.io.IOException;

import com.example.trabalhofinal.component.AppMenu;
import com.example.trabalhofinal.controller.UsuariosController;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;
import com.example.trabalhofinal.util.ResourceUtil;

public class MenuAdmComponent extends AppMenu {

	private final UsuariosController usuariosController;
	private final TabMenuDelegate delegate;

	public MenuAdmComponent(TabMenuDelegate delegate) throws IOException {
		super(bundle.getString("label.administracao"), ResourceUtil.icon("setting"));
		this.delegate = delegate;
		this.usuariosController = new UsuariosController();
		init();
	}

	private void init() throws IOException {
		MenuItem users = novoMenuItem(bundle.getString("label.usuarios"), "users");
		users.setOnAction(aE -> delegate.trocarConteudo(usuariosController.getUsuariosTab()));
		getItems().add(users);
	}

	public UsuariosController getUsuariosController() {
		return usuariosController;
	}

	@Override public Tab tabInicial() {
		return usuariosController.getUsuariosTab();
	}
}
