package com.example.trabalhofinal.service;

import java.util.List;

import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;
import com.example.trabalhofinal.repository.CardapioRepository;

public class CardapitoService {

	private final CardapioRepository repository;

	public CardapitoService() {
		this.repository = CardapioRepository.getInstance();
	}

	public List<Cardapio> listarPorTipo(CardapioTipo tipo) {
		return repository.listarPorTipo(tipo);
	}

	public boolean salvar(Cardapio cardapio) {
		try {
			return repository.salvar(cardapio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean atualizar(Cardapio cardapio) {
		try {
			return repository.atualizar(cardapio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
