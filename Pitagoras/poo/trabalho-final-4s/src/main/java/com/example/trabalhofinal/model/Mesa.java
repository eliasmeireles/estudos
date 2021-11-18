package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "mesa")
public class Mesa {

	@Property(name = "mesa_id", primaryKey = true)
	private Integer mesaId;

	@Property(name = "numero", type = "INT NOT NULL UNIQUE")
	private Integer numero;

	@Property(name = "disponivel", type = "BOOLEAN NOT NULL DEFAULT TRUE")
	private boolean disponivel;

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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}
