package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.view.UsuariosTab;

public class UsuariosController implements UsuariosTab.UsuarioTabDelegate {

	private final UsuariosTab usuariosTab;

	public UsuariosController() {
		this.usuariosTab = new UsuariosTab(this);
	}

	public UsuariosTab getUsuariosTab() {
		return usuariosTab;
	}

	@Override public void onUsuarioSelecionado(Usuario usuario) {

	}

	@Override public void cadastrar(String nome, String login, String senha) {
		Usuario usuario = new Usuario(nome, senha, login);
		System.out.println(usuario);

		usuariosTab.limparUsuarioForm();
	}
}
