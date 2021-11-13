package com.example.trabalhofinal.service;

import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.repository.UsuarioRepository;

public class UsuarioService {

//	public final UsuarioRepository repository;
//
//	public UsuarioService() {
//		this.repository = new UsuarioRepository();
//	}
//
//	public Usuario login(String login, String senha) {
//		return repository.findByLoginAndPassword(login, senha);
//	}

	public Usuario login(String login, String senha) {
		return new Usuario();
	}
}
