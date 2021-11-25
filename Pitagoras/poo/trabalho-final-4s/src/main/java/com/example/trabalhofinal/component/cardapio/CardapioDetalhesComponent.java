package com.example.trabalhofinal.component.cardapio;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Optional;

import com.example.trabalhofinal.App;
import com.example.trabalhofinal.authority.UsuarioAuthority;
import com.example.trabalhofinal.component.HLabelValorComponent;
import com.example.trabalhofinal.component.VLabelValorComponent;
import com.example.trabalhofinal.component.facoty.MenuBuilder;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.Usuario;

public class CardapioDetalhesComponent extends VBox implements MenuBuilder.MenuCardapio {

	private final Cardapio cardapio;
	private final ListaCardapioComponent.CardapioDelegate delegate;
	private final VBox cardapioData;

	public CardapioDetalhesComponent(Cardapio cardapio, ListaCardapioComponent.CardapioDelegate delegate) {
		super(new VBox());
		this.cardapio = cardapio;
		this.delegate = delegate;
		this.cardapioData = new VBox();
		setupComponent();
		reajustar();
	}

	private void reajustar() {
		App.mainStage.widthProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::setupComponent));
		App.mainStage.heightProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::setupComponent));
	}

	private void setupComponent() {
		getChildren().clear();
		cardapioData.getChildren().clear();
		final ImageView imageView = new ImageView(new Image(cardapio.getImagem(), true));
		imageView.setFitWidth(455);
		imageView.setFitHeight(455);
		getChildren().add(imageView);
		setAlignment(Pos.CENTER);
		setSpacing(8);
		setPadding(new Insets(16));
		getChildren().add(cardapioData);
		cardapioData.setAlignment(Pos.CENTER);
		cardapioData.setSpacing(5);
		cardapioData.getChildren().add(new HLabelValorComponent(bundle.getString("label.nome"), cardapio.getNome()));
		cardapioData.getChildren().add(new HLabelValorComponent(bundle.getString("label.preco"), String.valueOf(cardapio.getPreco())));
		final VLabelValorComponent labelValorComponent =
				new VLabelValorComponent(bundle.getString("label.ingredientes"), String.valueOf(cardapio.getIngredientes()));
		setMaxWidth(App.mainStage.getWidth() - 325);
		cardapioData.getChildren().add(labelValorComponent);
		getChildren().add(menuBuild(this));
	}

	private Pane menuBuild(MenuBuilder.MenuCardapio detalhesListener) {
		final Optional<Usuario> usuarioLogado = UsuarioAuthority.getUsuarioLogado();
		if (usuarioLogado.isPresent()) {
			return usuarioLogado.get()
					.getPermissao()
					.menuBuilder
					.cardapioMenu(detalhesListener);
		}
		return new Pane();
	}

	@Override public void sair() {
		delegate.sair();
	}

	@Override public void editar() {
		delegate.editarElemento(cardapio);
	}

}
