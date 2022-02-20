package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.usuario.UsuariosTabComponent;
import com.example.trabalhofinal.model.ServiceResponse;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.service.UsuarioService;

public class UsuariosController implements UsuariosTabComponent.UsuarioTabDelegate {

	private final UsuariosTabComponent usuariosTab;
	private final UsuarioService service;

	public UsuariosController() {
		this.service = new UsuarioService();
		this.usuariosTab = new UsuariosTabComponent(this);
		usuariosTab.setElementos(service.findAll());
	}

	public UsuariosTabComponent getUsuariosTab() {
		return usuariosTab;
	}

	@Override public void cadastrarElemento(Usuario elemento) {
		usuariosTab.dismisAlert();
		final ServiceResponse serviceResponse = service.salvar(elemento);

		if (serviceResponse.isSucesso()) {
			usuariosTab.showSuccessAlert(serviceResponse.getMensagem());
			usuariosTab.limparUsuarioForm();
			usuariosTab.setElementos(service.findAll());
		} else {
			usuariosTab.showErrorAlert(serviceResponse.getMensagem());
		}
	}

	@Override public void mostrarElemento(Usuario elemento) {

	}

	@Override public void editarElemento(Usuario elemento) {

	}

	@Override public void selecionarElemento(Usuario elemento) {
		usuariosTab.setUsuario(elemento);
	}
}
