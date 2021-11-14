package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.model.ServiceResponse;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.service.UsuarioService;
import com.example.trabalhofinal.view.UsuariosTab;

public class UsuariosController implements UsuariosTab.UsuarioTabDelegate {

	private final UsuariosTab usuariosTab;
	private final UsuarioService service;

	public UsuariosController() {
		this.service = new UsuarioService();
		this.usuariosTab = new UsuariosTab(this);
	}

	public UsuariosTab getUsuariosTab() {
		return usuariosTab;
	}

	@Override public void onUsuarioSelecionado(Usuario usuario) {

	}

	@Override public void cadastrar(String nome, String login, String senha) {
		usuariosTab.dismisAlert();
		Usuario usuario = new Usuario(nome, senha, login);
		final ServiceResponse serviceResponse = service.salvar(usuario);

		if (serviceResponse.isSucesso()) {
			usuariosTab.showSuccessAlert(serviceResponse.getMensagem());
			usuariosTab.limparUsuarioForm();
		} else {
			usuariosTab.showErrorAlert(serviceResponse.getMensagem());
		}
	}
}
