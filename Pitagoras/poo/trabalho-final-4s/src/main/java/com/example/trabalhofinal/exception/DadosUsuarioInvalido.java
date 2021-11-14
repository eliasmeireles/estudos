package com.example.trabalhofinal.exception;

import com.example.trabalhofinal.model.ServiceResponse;

public class DadosUsuarioInvalido extends Exception {
	private final ServiceResponse response;

	public DadosUsuarioInvalido(String message) {
		super(message);
		this.response = new ServiceResponse(message);
	}

	public ServiceResponse getResponse() {
		return response;
	}
}
