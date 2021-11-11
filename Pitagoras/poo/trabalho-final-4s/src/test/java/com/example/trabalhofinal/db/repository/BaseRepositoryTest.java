package com.example.trabalhofinal.db.repository;

import org.junit.jupiter.api.Test;

import com.example.trabalhofinal.model.UsuarioTest;

class BaseRepositoryTest {

	@Test
	void deve_GerarAQueryParaCriarUmaTabela_QuandoAClasseEstaConfiguradaCorretamente() {
		new UsuarioTestRepository().findAll();
	}

	class UsuarioTestRepository extends BaseRepository<UsuarioTest> {

	}
}
