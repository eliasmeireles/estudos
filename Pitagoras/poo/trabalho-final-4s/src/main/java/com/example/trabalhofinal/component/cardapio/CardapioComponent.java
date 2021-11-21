package com.example.trabalhofinal.component.cardapio;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.trabalhofinal.component.CardComponent;
import com.example.trabalhofinal.component.CircleImageVIew;
import com.example.trabalhofinal.component.HLabelValorComponent;
import com.example.trabalhofinal.model.Cardapio;

public class CardapioComponent extends CardComponent<HBox> {

	private final Cardapio cardapio;
	private final VBox userData;

	public CardapioComponent(Cardapio cardapio, CardapioTabComponent.CardapioDelegate delegate) {
		super(new HBox());
		this.cardapio = cardapio;
		this.userData = new VBox();
		setupComponent();
		setOnMouseClicked(eH -> delegate.mostrarElemento(this.cardapio));
	}

	private void setupComponent() {
		ImageView imageView = new CircleImageVIew(cardapio.getImagem(), 90, 90, 35);
		container.getChildren().add(imageView);
		container.setSpacing(8);
		container.getChildren().add(userData);
		userData.setAlignment(Pos.CENTER);
		userData.setSpacing(5);
		userData.getChildren().add(new HLabelValorComponent(bundle.getString("label.nome"), cardapio.getNome()));
		userData.getChildren().add(new HLabelValorComponent(bundle.getString("label.preco"), String.valueOf(cardapio.getPreco())));
	}
}
