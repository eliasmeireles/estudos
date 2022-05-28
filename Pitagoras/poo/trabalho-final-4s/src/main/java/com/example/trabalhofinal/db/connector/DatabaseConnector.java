package com.example.trabalhofinal.db.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {

	private final Logger logger = Logger.getAnonymousLogger();

	private Connection connection;

	public static final DatabaseConnector connector = new DatabaseConnector();

	private DatabaseConnector() {

	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		if (connection == null) {
			String url = "jdbc:mysql://127.0.0.1:3306/isto_e_aquilo";
			String username = "root";
			String password = "5hmkC7GCZfv2";

			logger.log(Level.INFO, "Inicializando conexão com o banco: {0}", url);
			connection = DriverManager.getConnection(url, username, password);
			logger.log(Level.INFO, "Conexão realizada com sucesso.");
		}
		return connection;
	}
}
