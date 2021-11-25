package com.example.trabalhofinal.repository;

import java.util.List;

import com.example.trabalhofinal.db.repository.BaseRepository;
import com.example.trabalhofinal.model.Pedido;

public class PedidoRepository extends BaseRepository<Pedido, Integer> {

	@Override public List<Pedido> findAll() {
		return super.findAll(new StringBuilder(orderByFinalizado()));
	}

	public List<Pedido> findByFinalizado(boolean finalizado) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("finalizado"))
				.append(orderByFinalizado());

		return findAll(query, finalizado);
	}

	public List<Pedido> findByFinalizadoAndMesa(boolean finalizado, int mesaId) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("finalizado "))
				.append(" AND ")
				.append(fieldFilter("mesa_id"))
				.append(orderByFinalizado());

		return findAll(query, finalizado, mesaId);
	}

	public List<Pedido> findByMesa(int mesaId) {
		final StringBuilder query = new StringBuilder("WHERE ")
				.append(fieldFilter("mesa_id"))
				.append(orderByFinalizado());

		return findAll(query, mesaId);
	}

	private String orderByFinalizado() {
		return " ORDER BY finalizado ASC";
	}
}
