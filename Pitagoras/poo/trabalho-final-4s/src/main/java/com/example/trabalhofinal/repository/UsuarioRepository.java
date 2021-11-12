package com.example.trabalhofinal.repository;

import java.util.List;

import com.example.trabalhofinal.db.repository.BaseRepository;
import com.example.trabalhofinal.model.Usuario;

public class UsuarioRepository extends BaseRepository<Usuario> {

	public Usuario findByLoginAndPassword(String login, String password) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("login"))
				.append(" AND ")
				.append(fieldFilter("senha"));

		final List<Usuario> usuarios = findAll(query.toString(), login, password);
		return usuarios.stream().findFirst().orElse(null);

	}
}
