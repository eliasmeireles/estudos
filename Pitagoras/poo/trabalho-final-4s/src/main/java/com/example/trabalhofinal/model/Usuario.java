package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "user")
public class Usuario {

	@Property(name = "user_id", primaryKey = true)
	private int userId;

	@Property(name = "username", type = "VARCHAR(100) NOT NULL UNIQUE")
	private String username;

	@Property(name = "password", type = "VARCHAR(16) NOT NULL")
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
