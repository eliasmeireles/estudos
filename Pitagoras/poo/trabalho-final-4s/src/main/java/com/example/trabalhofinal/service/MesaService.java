package com.example.trabalhofinal.service;

import java.util.List;

import com.example.trabalhofinal.model.Mesa;
import com.example.trabalhofinal.repository.MesaRepository;

public class MesaService {

	private final MesaRepository repository;

	public MesaService() {
		this.repository = new MesaRepository();
	}

	public List<Mesa> findAll() {
		return repository.findAll();
	}

	public List<Mesa> findByDisponivel(boolean disponivel) {
		return repository.findByDisponivel(disponivel);
	}

	public boolean salvar(Mesa mesa) {
		if (mesa.getMesaId() != null) {
			return atualizar(mesa);
		}
		try {
			return repository.salvar(mesa) != null;
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}

	public boolean atualizar(Mesa mesa) {
		try {
			return repository.salvar(mesa) != null;
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return false;
	}
}
