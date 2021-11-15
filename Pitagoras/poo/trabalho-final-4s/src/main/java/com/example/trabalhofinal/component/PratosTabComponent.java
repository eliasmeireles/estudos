package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

public class PratosTabComponent extends AppTabComponent {

	protected PratosTabComponent() {
		super(String.format("%s -> %s", bundle.getString("label.cardapio"), bundle.getString("label.pratos")));
	}
}
