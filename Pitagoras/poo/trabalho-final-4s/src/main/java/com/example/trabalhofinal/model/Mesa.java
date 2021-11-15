package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "mesa")
public class Mesa {

	@Property(name = "mesa_id", primaryKey = true)
	private Integer mesaId;

	@Property(name = "numero", type = "INT NOT NULL UNIQUE")
	private Integer numero;

	@Property(name = "numero_cadeiras", type = "INT NOT NULL")
	private Integer numeroCadeiras;

	@Property(name = "ativa", type = "BOOLEAN DEFAULT TRUE")
	private boolean ativa;

	public Integer getMesaId() {
		return mesaId;
	}

	public void setMesaId(Integer mesaId) {
		this.mesaId = mesaId;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumeroCadeiras() {
		return numeroCadeiras;
	}

	public void setNumeroCadeiras(Integer numeroCadeiras) {
		this.numeroCadeiras = numeroCadeiras;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
}
