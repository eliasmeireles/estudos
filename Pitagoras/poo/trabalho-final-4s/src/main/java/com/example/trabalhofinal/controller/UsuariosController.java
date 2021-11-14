package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.UsuariosTabComponent;
import com.example.trabalhofinal.model.ServiceResponse;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.service.UsuarioService;

public class UsuariosController implements UsuariosTabComponent.UsuarioTabDelegate {

	private final UsuariosTabComponent usuariosTab;
	private final UsuarioService service;

	public UsuariosController() {
		this.service = new UsuarioService();
		this.usuariosTab = new UsuariosTabComponent(this);
		usuariosTab.setUsuarios(service.findAll());
	}

	public UsuariosTabComponent getUsuariosTab() {
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
			usuariosTab.setUsuarios(service.findAll());
		} else {
			usuariosTab.showErrorAlert(serviceResponse.getMensagem());
		}
	}
}
