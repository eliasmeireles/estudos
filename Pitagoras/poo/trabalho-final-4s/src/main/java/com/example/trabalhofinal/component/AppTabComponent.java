package com.example.trabalhofinal.component;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.util.List;

import com.example.trabalhofinal.App;

public abstract class AppTabComponent<T, D extends AppTabComponent.TabMenuDelegate<?>> extends Tab {

	protected final ScrollPane scrollPane;
	private final AppAlertComponent appAlertComponent;
	protected final ListaComponent<T> listaComponent;
	private final VBox container;

	protected AppTabComponent(D delegate, String tabTitulo) {
		super(tabTitulo);
		this.container = new VBox();
		this.appAlertComponent = new AppAlertComponent(this::dismisAlert);
		this.listaComponent = listaComponentBuilder(delegate);
		this.scrollPane = new ScrollPane(listaComponent);
		configurarContainer();
		reajustarView();
	}

	public void setElementos(List<T> elementos) {
		this.listaComponent.setElementos(elementos);
	}

	protected abstract ListaComponent<T> listaComponentBuilder(D delegate);

	public void showErrorAlert(String mensagem) {
		appAlertComponent.setErrorMessage(mensagem);
		container.getChildren().add(0, appAlertComponent);
	}

	public void showSuccessAlert(String mensagem) {
		appAlertComponent.setSuccessMensage(mensagem);
		container.getChildren().remove(appAlertComponent);
		container.getChildren().add(0, appAlertComponent);
	}

	public void dismisAlert() {
		container.getChildren().remove(appAlertComponent);
	}

	private void configurarContainer() {
		container.setAlignment(Pos.TOP_CENTER);
		container.setSpacing(2);
		container.setFillWidth(true);
		setContent(container);
	}

	private void reajustarView() {
		App.mainStage.widthProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::resize));
		App.mainStage.heightProperty().addListener((observableValue, number, t1) -> Platform.runLater(this::resize));
	}

	protected void resize() {
		scrollPane.setMinWidth(App.mainStage.getWidth() - 220);
		scrollPane.setMinHeight(App.mainStage.getHeight() - 220);
	}

	public final void setRoot(Node node) {
		container.getChildren().add(node);
	}

	public interface TabMenuDelegate<E> {

		void cadastrarElemento(E elemento);

		void mostrarElemento(E elemento);

		void editarElemento(E elemento);

		void selecionarElemento(E elemento);
	}
}
