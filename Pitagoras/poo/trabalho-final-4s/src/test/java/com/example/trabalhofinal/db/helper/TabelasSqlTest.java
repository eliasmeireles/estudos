package com.example.trabalhofinal.db.helper;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.example.trabalhofinal.model.Usuario;

class TabelasSqlTest {

	@Test
	void deve_GerarAQueryParaCriarUmaTabela_QuandoAClasseEstaConfiguradaCorretamente() throws SQLException, ClassNotFoundException {
		TabelaHelper.createTable(Usuario.class);
	}
}
