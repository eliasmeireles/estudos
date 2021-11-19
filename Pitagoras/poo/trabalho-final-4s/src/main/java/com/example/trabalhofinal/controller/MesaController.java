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
		tabComponent.setMesas(service.findByDisponivel(true));
		return tabComponent;
	}

	@Override public void mesaSelecionada(Mesa mesa) {

	}
}
