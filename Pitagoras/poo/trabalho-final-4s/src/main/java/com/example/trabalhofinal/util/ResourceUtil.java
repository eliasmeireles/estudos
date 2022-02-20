package com.example.trabalhofinal.util;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;

import com.example.trabalhofinal.App;

public class ResourceUtil {

	private ResourceUtil() {

	}

	public static ImageView icon(String icon, double width, double height) throws IOException {
		final URL resource = App.class.getResource(String.format("icons/%s.png", icon));
		if (resource != null) {
			final ImageView imageView = new ImageView(resource.toExternalForm());
			imageView.setFitHeight(height);
			imageView.setFitWidth(width);
			return imageView;
		}
		throw new IOException("File not found");
	}

	public static ImageView icon(String icon) throws IOException {
		final URL resource = App.class.getResource(String.format("icons/%s.png", icon));
		if (resource != null) {
			final ImageView imageView = new ImageView(resource.toExternalForm());
			imageView.setFitHeight(16);
			imageView.setFitWidth(16);
			return imageView;
		}
		throw new IOException("File not found");
	}

}
