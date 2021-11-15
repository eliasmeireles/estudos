package com.example.trabalhofinal.component;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.util.ResourceUtil;

public class MenuSair extends VBox {

	public MenuSair(Listener listener) {
		setId("menu-item");
		try {
			getChildren().add(ResourceUtil.icon("exit", 35, 35));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAlignment(Pos.CENTER);
		setMinWidth(55);
		setMinHeight(55);
		setOnMouseClicked(eH -> listener.sair());
	}

	public interface Listener {
		void sair();
	}
}
