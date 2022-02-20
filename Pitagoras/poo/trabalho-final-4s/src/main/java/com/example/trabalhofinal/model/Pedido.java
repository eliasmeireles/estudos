package com.example.trabalhofinal.model;

import java.util.List;

import com.example.trabalhofinal.db.annotation.OneToMany;
import com.example.trabalhofinal.db.annotation.OneToOne;
import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "pedidos")
public class Pedido {

	@Property(name = "pedido_id", primaryKey = true)
	private Integer pedidoId;

	@Property(name = "valor_total", type = "DOUBLE NOT NULL")
	private Double valorTotal;

	@Property(name = "finalizado", type = "BOOLEAN NOT NULL DEFAULT false")
	private boolean finalizado;

	@OneToOne
	private Mesa mesa;

	@OneToMany(target = Cardapio.class)
	private List<Cardapio> cardapios;

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}
}
