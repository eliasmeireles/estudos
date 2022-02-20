package com.example.trabalhofinal.component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleImageVIew extends ImageView {

	public CircleImageVIew(String url, double width, double height, double radius) {
		super(new Image(url, true));
		setSmooth(true);
		setFitWidth(width);
		setFitHeight(height);

		Circle circle = new Circle(radius);
		circle.setStroke(Color.WHITESMOKE);
		circle.setStrokeWidth(8);
		circle.setCenterX(getFitWidth() / 2);
		circle.setCenterY(getFitHeight() / 2);
		setClip(circle);
	}
}
