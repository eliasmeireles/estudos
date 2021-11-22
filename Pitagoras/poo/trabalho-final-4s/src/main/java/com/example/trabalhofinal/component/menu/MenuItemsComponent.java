package com.example.trabalhofinal.component.menu;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MenuItemsComponent extends HBox {

	private final Label label;

	public MenuItemsComponent(int total) {
		label = new Label(totalStringValue(total));
		label.setId("label-24-px");
		setSpacing(4);
		setAlignment(Pos.CENTER_LEFT);
	}

	public void build(MenuItemDelegate delegate) {
		getChildren().add(new MenuAdd(delegate, 45));
		getChildren().add(new MenuRemove(delegate, 45));
		getChildren().add(label);
	}


	public void atualizar(int total) {
		label.setText(totalStringValue(total));
	}

	private String totalStringValue(int total) {
		return String.format(bundle.getString("label.total.placeholder"), total);
	}

	public interface MenuItemDelegate extends MenuActions.MenuAdd, MenuActions.MenuRemover {

	}
}
