package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.PropertyAdapter;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "usuarios")
public class Usuario {

	@Property(name = "usuario_id", primaryKey = true)
	private Integer userId;

	@Property(name = "nome", type = "VARCHAR(100) NOT NULL")
	private String nome;

	@Property(name = "senha", type = "VARCHAR(16) NOT NULL")
	private String senha;

	@Property(name = "login", type = "VARCHAR(20) NOT NULL UNIQUE")
	private String login;

	@PropertyAdapter(adapter = Permissao.PermissaoAdapter.class)
	private Permissao permissao;

	public Usuario() {
	}

	public Usuario(Integer userId, String nome, String senha, String login, Permissao permissao) {
		this.userId = userId;
		this.nome = nome;
		this.senha = senha;
		this.login = login;
		this.permissao = permissao;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override public String toString() {
		return "Usuario{" +
				"userId=" + userId +
				", nome='" + nome + '\'' +
				", senha='" + senha + '\'' +
				", login='" + login + '\'' +
				'}';
	}
}
