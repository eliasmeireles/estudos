package com.example.trabalhofinal.interator;

import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.service.UsuarioService;

public class LoginInteractor {

	private final UsuarioService usuarioService;
	private final LoginDelegate delegate;

	public LoginInteractor(LoginDelegate delegate) {
		this.delegate = delegate;
		this.usuarioService = new UsuarioService();
	}

	public interface LoginDelegate {
		void sucesso();

		void falha();
	}

	public void login(String login, String senha) {
		final Usuario usuario = usuarioService.login(login, senha);

		if (usuario != null) {
			delegate.sucesso();
		} else {
			delegate.falha();
		}
	}
}
