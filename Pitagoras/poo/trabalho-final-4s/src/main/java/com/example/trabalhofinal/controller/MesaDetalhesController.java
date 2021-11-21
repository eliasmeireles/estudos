package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.mesa.MesaDetalhesComponent;
import com.example.trabalhofinal.component.mesa.MesaTabComponent;
import com.example.trabalhofinal.model.Mesa;

public class MesaDetalhesController {

	private final Mesa mesa;
	private final MesaDetalhesComponent component;

	public MesaDetalhesController(Mesa mesa, MesaTabComponent.MesaDelegate delegate) {
		this.mesa = mesa;
		this.component = new MesaDetalhesComponent(mesa, delegate);
	}

	public MesaDetalhesComponent getComponent() {
		return component;
	}
}
