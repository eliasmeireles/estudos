package com.example.trabalhofinal.controller;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.authority.UsuarioAuthority;
import com.example.trabalhofinal.component.mesa.MesaTabComponent;
import com.example.trabalhofinal.model.Mesa;
import com.example.trabalhofinal.service.MesaService;

public class MesaController implements MesaTabComponent.MesaDelegate {

	private final MesaService service;
	private final MesaTabComponent tabComponent;

	public MesaController() {
		this.service = new MesaService();
		this.tabComponent = new MesaTabComponent(this);
	}

	public MesaTabComponent getTab() {
		tabComponent.setElementos(service.findByDisponivel(true));
		return tabComponent;
	}

	@Override public void cadastrarElemento(Mesa elemento) {
		if (elemento.getNumero() != null) {
			if (service.salvar(elemento)) {
				tabComponent.showSuccessAlert(bundle.getString("label.cadastro.salvo.sucesso"));
			} else {
				tabComponent.showErrorAlert(bundle.getString("label.cadastro.falha"));
			}
		}
	}

	@Override public void mostrarElemento(Mesa elemento) {
		tabComponent.mesaDetalhes(elemento);
	}

	@Override public void editarElemento(Mesa elemento) {

	}

	@Override public void selecionarElemento(Mesa elemento) {

	}

	@Override public void sair() {
		tabComponent.listarMesas();
		tabComponent.setElementos(service.findByDisponivel(true));
	}

	@Override public void editar() {

	}

	@Override public boolean ehAdministrador() {
		return UsuarioAuthority.ehAdm();
	}
}
