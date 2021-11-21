package com.example.trabalhofinal.controller;

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

	}

	@Override public void mostrarElemento(Mesa elemento) {

	}

	@Override public void editarElemento(Mesa elemento) {

	}

	@Override public void selecionarElemento(Mesa elemento) {

	}
}
