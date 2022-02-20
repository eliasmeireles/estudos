package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "configuracao")
public class Configuracao {

	@Property(name = "configuracao_id", primaryKey = true)
	private int configuracaoId;
	private String cor;
	private float speed;

	public int getConfiguracaoId() {
		return configuracaoId;
	}

	public void setConfiguracaoId(int configuracaoId) {
		this.configuracaoId = configuracaoId;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
