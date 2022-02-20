package com.example.trabalhofinal.component.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.util.ResourceUtil;

public class AppMenu extends VBox {
	public AppMenu(String iconName) {
		init(iconName, 50);
	}

	public AppMenu(String iconName, double size) {
		init(iconName, size);
	}

	private void init(String iconName, double size) {
		setId("menu-item");
		try {
			getChildren().add(ResourceUtil.icon(iconName, size - 20, size - 20));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAlignment(Pos.CENTER);
		setPrefSize(size, size);
	}
}
