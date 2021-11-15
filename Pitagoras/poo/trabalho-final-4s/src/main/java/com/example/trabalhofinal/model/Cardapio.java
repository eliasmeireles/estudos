package com.example.trabalhofinal.model;

import com.example.trabalhofinal.db.annotation.Property;
import com.example.trabalhofinal.db.annotation.PropertyAdapter;
import com.example.trabalhofinal.db.annotation.Table;

@Table(name = "cardapio")
public class Cardapio {

	@Property(name = "cardapio_id", primaryKey = true)
	private Integer cardapioId;
	private String nome;
	private String ingredientes;
	private double preco;
	private String imagem;

	@PropertyAdapter(adapter = CardapioTipo.CardapioAdapter.class)
	private CardapioTipo tipo;

	public Integer getCardapioId() {
		return cardapioId;
	}

	public void setCardapioId(Integer cardapioId) {
		this.cardapioId = cardapioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public CardapioTipo getTipo() {
		return tipo;
	}

	public void setTipo(CardapioTipo tipo) {
		this.tipo = tipo;
	}

}
