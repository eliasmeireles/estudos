package com.example.trabalhofinal.component.logout;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import com.example.trabalhofinal.component.AppTabComponent;
import com.example.trabalhofinal.component.ListaComponent;

public class TabLogout extends AppTabComponent<Object, TabLogout.LogoutDelegate> {

	protected TabLogout(LogoutDelegate delegate) {
		super(delegate, bundle.getString("label.sair"));
	}

	@Override protected ListaComponent<Object> listaComponentBuilder(LogoutDelegate delegate) {
		return null;
	}

	public interface LogoutDelegate extends TabMenuDelegate<Object> {
		void logout();

		default @Override void cadastrarElemento(Object elemento) {
		}

		default @Override void mostrarElemento(Object elemento) {
		}

		default @Override void editarElemento(Object elemento) {
		}

		default @Override void selecionarElemento(Object elemento) {
		}
	}
}
