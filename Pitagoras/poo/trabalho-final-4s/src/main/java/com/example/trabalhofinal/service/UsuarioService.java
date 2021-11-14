package com.example.trabalhofinal.service;

import static com.example.trabalhofinal.config.ResourceConfig.bundle;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.example.trabalhofinal.exception.DadosUsuarioInvalido;
import com.example.trabalhofinal.model.ServiceResponse;
import com.example.trabalhofinal.model.Usuario;
import com.example.trabalhofinal.repository.UsuarioRepository;

public class UsuarioService {

	private static UsuarioService instance;

	private Usuario usuarioLogado;
	public final UsuarioRepository repository;

	private UsuarioService() {
		this.repository = UsuarioRepository.getInstance();
	}

	public static UsuarioService getInstance() {
		if (instance == null) {
			instance = new UsuarioService();
		}
		return instance;
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario login(String login, String senha) {
		usuarioLogado = repository.findByLoginAndPassword(login, senha);
		return usuarioLogado;
	}

	public void logout() {
		this.usuarioLogado = null;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public ServiceResponse salvar(Usuario usuario) {
		try {
			if (validaUsuario(usuario) && repository.savar(usuario)) {
				return new ServiceResponse(true, bundle.getString("label.usuario.salvo"));
			}
		} catch (DadosUsuarioInvalido dadosUsuarioInvalido) {
			return dadosUsuarioInvalido.getResponse();
		} catch (Exception e) {
			e.printStackTrace();
			if (ehViolacaoDeUniqueConstraint(e)) {
				return new ServiceResponse(bundle.getString("label.falha.salvar.usuario.login.duplicado"));
			}
		}
		return new ServiceResponse(bundle.getString("label.falha.salvar.usuario"));
	}

	public ServiceResponse atulizarUsuario(Usuario usuario) {
		try {
			if (validaUsuario(usuario) && repository.atualizar(usuario)) {
				return new ServiceResponse(true, bundle.getString("label.usuario.atualizado"));
			}
		} catch (DadosUsuarioInvalido dadosUsuarioInvalido) {
			return dadosUsuarioInvalido.getResponse();
		} catch (Exception e) {
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

	private boolean validaUsuario(Usuario usuario) throws DadosUsuarioInvalido {
		if (usuario == null || usuario.getLogin() == null || usuario.getLogin().isBlank() || usuario.getNome() == null || usuario.getNome().isBlank()) {
			throw new DadosUsuarioInvalido(bundle.getString("label.usuario.invalido"));
		} else if (usuario.getSenha().isBlank()) {
			throw new DadosUsuarioInvalido(bundle.getString("label.senha.requerida"));
		} else if (usuario.getSenha().trim().length() < 6 || usuario.getSenha().trim().length() > 16) {
			throw new DadosUsuarioInvalido(bundle.getString("label.senha.senha.invalida"));
		}
		return true;
	}
}
