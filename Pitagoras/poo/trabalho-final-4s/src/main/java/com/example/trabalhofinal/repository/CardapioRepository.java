package com.example.trabalhofinal.repository;

import java.util.List;

import com.example.trabalhofinal.db.repository.BaseRepository;
import com.example.trabalhofinal.model.Cardapio;
import com.example.trabalhofinal.model.CardapioTipo;

public class CardapioRepository extends BaseRepository<Cardapio, Integer> {

	private static CardapioRepository instance;

	private CardapioRepository() {

	}

	public static CardapioRepository getInstance() {
		if (instance == null) {
			instance = new CardapioRepository();
		}
		return instance;
	}

	public List<Cardapio> listarPorTipo(CardapioTipo tipo) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("tipo"));
		return findAll(query, tipo.name());
	}
}
