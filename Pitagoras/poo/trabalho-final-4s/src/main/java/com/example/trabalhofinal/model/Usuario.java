package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "user")
public class Usuario {

	@Property(name = "user_id", primaryKey = true)
	private int userId;

	@Property(name = "nome", type = "VARCHAR(100) NOT NULL")
	private String nome;

	@Property(name = "senha", type = "VARCHAR(16) NOT NULL")
	private String senha;

	@Property(name = "login", type = "VARCHAR(20) NOT NULL UNIQUE")
	private String login;

	public Usuario() {
	}

	public Usuario(String nome, String senha, String login) {
		this.nome = nome;
		this.senha = senha;
		this.login = login;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	@Override public String toString() {
		return "Usuario{" +
				"userId=" + userId +
				", nome='" + nome + '\'' +
				", senha='" + senha + '\'' +
				", login='" + login + '\'' +
				'}';
	}
}
