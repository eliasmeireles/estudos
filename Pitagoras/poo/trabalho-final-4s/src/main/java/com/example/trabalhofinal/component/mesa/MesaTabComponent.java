package com.example.trabalhofinal.component.mesa;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.component.AppTabComponent;

public class MesaTabComponent extends AppTabComponent {

	private final MesaDelegate delegate;

	public MesaTabComponent(MesaDelegate delegate) {
		super(String.format("%s -> %s", bundle.getString("label.servicos"), bundle.getString("label.mesa")));
		this.delegate = delegate;
	}

	public interface MesaDelegate {

	}
}
