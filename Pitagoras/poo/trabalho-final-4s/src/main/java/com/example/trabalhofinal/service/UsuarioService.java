package com.example.trabalhofinal.service;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.example.trabalhofinal.model.ServiceResponse;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.repository.UsuarioRepository;

public class UsuarioService {

	public final UsuarioRepository repository;

	public UsuarioService() {
		this.repository = UsuarioRepository.getInstance();
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario login(String login, String senha) {
		return repository.findByLoginAndPassword(login, senha);
	}

	public ServiceResponse salvar(Usuario usuario) {
		try {
			if (repository.savar(usuario)) {
				return new ServiceResponse(true, bundle.getString("label.usuario.salvo"));
			}
		} catch (IllegalAccessException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			if (ehViolacaoDeUniqueConstraint(e)) {
				return new ServiceResponse(bundle.getString("label.falha.salvar.usuario.login.duplicado"));
			}
		}
		return new ServiceResponse(bundle.getString("label.falha.salvar.usuario"));
	}

	public ServiceResponse atulizarUsuario(Usuario usuario) {
		try {
			repository.atualizar(usuario);
			return new ServiceResponse(true, bundle.getString("label.usuario.atualizado"));
		} catch (IllegalAccessException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			if (ehViolacaoDeUniqueConstraint(e)) {
				return new ServiceResponse(bundle.getString("label.falha.atualizar.usuario.login.duplicado"));
			}
		}
		return new ServiceResponse(bundle.getString("label.falha.atualizar.usuario"));
	}

	private boolean ehViolacaoDeUniqueConstraint(Exception exception) {
		return exception instanceof SQLIntegrityConstraintViolationException;
	}
}
