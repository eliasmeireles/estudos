package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

public class CafesTabComponent extends AppTabComponent {

	protected CafesTabComponent() {
		super(String.format("%s -> %s", bundle.getString("label.cardapio"), bundle.getString("label.cafe")));
	}
}
