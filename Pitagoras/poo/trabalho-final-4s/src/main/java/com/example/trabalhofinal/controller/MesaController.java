package com.example.trabalhofinal.controller;

import com.example.trabalhofinal.component.mesa.MesaTabComponent;

public class MesaController implements MesaTabComponent.MesaDelegate {

	private final MesaTabComponent tabComponent;

	public MesaController() {
		this.tabComponent = new MesaTabComponent(this);
	}

	public MesaTabComponent getTab() {
		return tabComponent;
	}
}
