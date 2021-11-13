package com.example.trabalhofinal.util;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;

import com.example.trabalhofinal.App;

public class ResourceUtil {

	public static final ResourceUtil resource = new ResourceUtil();

	private ResourceUtil() {

	}

	public ImageView icon(String icon) throws IOException {
		final URL resource = App.class.getResource(String.format("icons/%s.png", icon));
		if (resource != null) {
			final ImageView imageView = new ImageView(resource.toExternalForm());
			imageView.setFitHeight(15);
			imageView.setFitWidth(15);
			return imageView;
		}
		throw new IOException("File not found");
	}

}
