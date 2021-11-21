package com.example.trabalhofinal.authority;

import java.util.Optional;

import com.example.trabalhofinal.model.Permissao;
import com.example.trabalhofinal.model.Usuario;

public class UsuarioAuthority {
	private static UsuarioAuthority instance;
	private Usuario usuarioLogado;

	private UsuarioAuthority() {
	}

	public static void init() {
		if (instance == null) {
			instance = new UsuarioAuthority();
		}
	}

	public static Optional<Usuario> getUsuarioLogado() {
		return Optional.ofNullable(UsuarioAuthority.instance.usuarioLogado);
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		UsuarioAuthority.instance.usuarioLogado = usuarioLogado;
	}

	public static void logout() {
		UsuarioAuthority.instance.usuarioLogado = null;
	}

	public static boolean ehAdm() {
		final Optional<Permissao> permissao = UsuarioAuthority.getUsuarioPermissao();
		return permissao.isPresent() && permissao.get().equals(Permissao.ADM);
	}

	public static Optional<Permissao> getUsuarioPermissao() {
		return UsuarioAuthority.instance.usuarioLogado != null ?
				Optional.of(UsuarioAuthority.instance.usuarioLogado.getPermissao()) : Optional.empty();
	}
}
