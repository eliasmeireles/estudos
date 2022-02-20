package com.example.trabalhofinal.repository;

import java.util.List;

import com.example.trabalhofinal.db.repository.BaseRepository;
import com.example.trabalhofinal.model.Mesa;

public class MesaRepository extends BaseRepository<Mesa, Integer> {

	public List<Mesa> findByDisponivel(boolean disponivel) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("disponivel"));
		return findAll(query, disponivel);
	}
}
