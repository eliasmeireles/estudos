package com.example.trabalhofinal.service;

import java.util.List;
import java.util.Optional;

import com.example.trabalhofinal.model.Pedido;
import com.example.trabalhofinal.repository.PedidoRepository;

public class PedidoService {

	private final PedidoRepository repository;

	public PedidoService() {
		repository = new PedidoRepository();
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public List<Pedido> findAllByFinalizado(boolean finalizado) {
		return repository.findByFinalizado(finalizado);
	}

	public List<Pedido> findAllByFinalizadoAndMesa(boolean finalizado, int mesaId) {
		return repository.findByFinalizadoAndMesa(finalizado, mesaId);
	}

	public Optional<Pedido> findPedidoAtivoDaMesa(int mesaId) {
		return repository.findByFinalizadoAndMesa(false, mesaId)
				.stream().findFirst();
	}

	public List<Pedido> findAllByMesa(int mesaId) {
		return repository.findByMesa(mesaId);
	}

	public boolean salvar(Pedido pedido) {
		if (pedido.getPedidoId() != null) {
			return atualizar(pedido);
		}
		try {
			repository.salvar(pedido);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean atualizar(Pedido pedido) {
		try {
			repository.atualizar(pedido);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deleteById(Integer id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
