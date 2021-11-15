package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

public class BebidasTabComponent extends AppTabComponent {

	protected BebidasTabComponent() {
		super(String.format("%s -> %s", bundle.getString("label.cardapio"), bundle.getString("label.pratos")));
	}
}
