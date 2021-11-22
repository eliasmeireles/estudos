package com.example.trabalhofinal.repository;

import java.util.List;

import com.example.trabalhofinal.db.repository.BaseRepository;
import com.example.trabalhofinal.model.Pedido;

public class PedidoRepository extends BaseRepository<Pedido, Integer> {

	public List<Pedido> findByFinalizado(boolean finalizado) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("finalizado"));

		return findAll(query, finalizado);
	}

	public List<Pedido> findByFinalizadoAndMesa(boolean finalizado, int mesaId) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("finalizado "))
				.append(" AND ")
				.append(fieldFilter("mesa_id"));

		return findAll(query, finalizado, mesaId);
	}

	public List<Pedido> findByMesa(int mesaId) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("mesa_id"));

		return findAll(query, mesaId);
	}
}
