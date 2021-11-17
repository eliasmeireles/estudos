package com.example.trabalhofinal.component.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.util.ResourceUtil;

public class MenuSelecionar extends VBox {

	public MenuSelecionar(Listener listener) {
		setId("menu-item");
		try {
			getChildren().add(ResourceUtil.icon("check", 35, 35));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAlignment(Pos.CENTER);
		setMinWidth(55);
		setMinHeight(55);
		setOnMouseClicked(eH -> listener.selecionar());
	}

	public interface Listener {
		void selecionar();
	}
}
