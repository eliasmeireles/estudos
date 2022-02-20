package com.example.trabalhofinal.db;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.trabalhofinal.db.connector.DatabaseConnector;

class DatabaseConnectorTest {

	private Connection connection;

	@BeforeEach
	void setup() throws SQLException, ClassNotFoundException {
		openMocks(this);
		connection = DatabaseConnector.connector.getConnection();
	}

	@Test
	void deveRetonarAConexaoComOBanco() {
		assertNotNull(connection);
	}

	@Test
	void setUser() throws SQLException {
		final ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user");

		while (resultSet.next()) {
			System.out.println(resultSet.getString("user_id") + " - " + resultSet.getString("username"));
		}
	}
}
