package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.util.ResourceUtil.resource;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ViewBuilder {

	public static MenuItem novoMenuItem(String nome, String icone) throws IOException {
		ImageView imageView = resource.icon(icone);
		imageView.setFitHeight(16);
		imageView.setFitWidth(16);
		return new MenuItem(nome, imageView);
	}

	public static MenuItem novoMenuItem(String nome, String icone, double width, double height) throws IOException {
		ImageView imageView = resource.icon(icone);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		return new MenuItem(nome, imageView);
	}
}
