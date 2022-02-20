package com.example.trabalhofinal.db.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.trabalhofinal.db.connector.DatabaseConnector;
import com.example.trabalhofinal.model.UsuarioTest;

class BaseRepositoryTest {

	@BeforeEach
	void clear() throws SQLException, ClassNotFoundException {
		List<String> queries = new ArrayList<>();
		queries.add("SET FOREIGN_KEY_CHECKS = 0");
		queries.add("drop table if exists cidade");
		queries.add("drop table if exists configuracao");
		queries.add("drop table if exists endereco");
		queries.add("drop table if exists estado");
		queries.add("drop table if exists pais");
		queries.add("drop table if exists usuario_test");
		queries.add("drop table if exists usuario_test_enderecos");
		queries.add("SET FOREIGN_KEY_CHECKS = 1");

		for (String query : queries) {
			DatabaseConnector.connector.getConnection().createStatement().execute(query);
		}
	}

	@Test
	void deve_GerarAQueryParaCriarUmaTabela_QuandoAClasseEstaConfiguradaCorretamente() throws SQLException, ClassNotFoundException {
		UsuarioTestRepository repository = new UsuarioTestRepository();
		System.out.println(repository.selectAllQuery);
//		ResultSet resultSet = DatabaseConnector.connector.getConnection()
//				.createStatement()
//				.executeQuery(repository.selectAllQuery);
//
//		repository.montarObjeto(resultSet);
	}

	class UsuarioTestRepository extends BaseRepository<UsuarioTest, Integer> {

	}
}
