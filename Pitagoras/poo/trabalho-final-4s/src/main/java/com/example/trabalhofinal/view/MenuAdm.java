package com.example.trabalhofinal.view;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import static com.example.trabalhofinal.util.ResourceUtil.resource;
import static com.example.trabalhofinal.view.ViewBuilder.novoMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;

import com.example.trabalhofinal.controller.UsuariosController;
import com.example.trabalhofinal.controller.delegate.TabMenuDelegate;

public class MenuAdm extends Menu {

	private final UsuariosController usuariosController;
	private final TabMenuDelegate delegate;

	public MenuAdm(TabMenuDelegate delegate) throws IOException {
		super(bundle.getString("label.administracao"), resource.icon("setting"));
		this.delegate = delegate;
		this.usuariosController = new UsuariosController();
		init();
	}

	private void init() throws IOException {
		MenuItem users = novoMenuItem(bundle.getString("label.usuarios"), "users");
		users.setOnAction(aE -> delegate.trocarConteudo(usuariosController.getUsuariosTab()));
		getItems().add(users);
	}
}
