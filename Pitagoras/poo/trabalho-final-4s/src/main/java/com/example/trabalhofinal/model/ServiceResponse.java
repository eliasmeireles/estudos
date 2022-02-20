package com.example.trabalhofinal.model;

public class ServiceResponse {
	private final boolean sucesso;
	private final String mensagem;

	public ServiceResponse(boolean sucesso, String mensagem) {
		this.sucesso = sucesso;
		this.mensagem = mensagem;
	}

	public ServiceResponse(String mensagem) {
		this.sucesso = false;
		this.mensagem = mensagem;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}
}
