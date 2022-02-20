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
		if (cardapio.getCardapioId() != null) {
			return atualizar(cardapio);
		}
		try {
			repository.salvar(cardapio);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean atualizar(Cardapio cardapio) {
		try {
			repository.atualizar(cardapio);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
