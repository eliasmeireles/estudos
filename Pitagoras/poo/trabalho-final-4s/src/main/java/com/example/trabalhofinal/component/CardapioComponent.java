package com.example.trabalhofinal.component;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import com.example.trabalhofinal.model.Cardapio;

public class CardapioComponent extends CardComponent<HBox> {

	private final Cardapio cardapio;
	private final VBox userData;

	public CardapioComponent(Cardapio cardapio, CardapioTabComponent.CardapioDelegate delegate) {
		super(new HBox());
		this.cardapio = cardapio;
		this.userData = new VBox();
		setupComponent();
		setOnMouseClicked(eH -> delegate.onCardapioSelecionado(this.cardapio));
	}

	private void setupComponent() {
		ImageView imageView = new ImageView(new Image(cardapio.getImagem(), true));

		imageView.setSmooth(true);
		imageView.setFitWidth(90);
		imageView.setFitHeight(90);

		Circle circle = new Circle(35);
		circle.setStroke(Color.WHITESMOKE);
		circle.setStrokeWidth(8);
		circle.setCenterX(imageView.getFitWidth() / 2);
		circle.setCenterY(imageView.getFitHeight() / 2);
		imageView.setClip(circle);

		container.getChildren().add(imageView);

		container.setAlignment(Pos.CENTER);
		container.setSpacing(8);
		container.getChildren().add(userData);
		userData.setAlignment(Pos.CENTER);
		userData.setSpacing(5);
		userData.getChildren().add(new LabelValorComponent(bundle.getString("label.nome"), cardapio.getNome()));
		userData.getChildren().add(new LabelValorComponent(bundle.getString("label.preco"), String.valueOf(cardapio.getPreco())));
	}
}
