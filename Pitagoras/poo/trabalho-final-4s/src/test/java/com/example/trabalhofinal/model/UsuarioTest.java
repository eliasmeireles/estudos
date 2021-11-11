package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.ForeignKey;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "usuario_test")
public class UsuarioTest {

	@Property(name = "usuario_id", primaryKey = true)
	private int usuarioId;
	private String nome;
	private String email;
	private String cpf;
	@ForeignKey
	private Endereco endereco;

	@ForeignKey
	private Configuracao configuracao;

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
}
