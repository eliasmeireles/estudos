package contas

import "src/models/contas/src/models/titular"

type PagarBoleto interface {
	Sacar(valor float64) (bool, string, float64)
}

type ContaCorrente struct {
	Titular       titular.Titular
	NumeroAgencia int
	NumeroConta   int
	saldo         float64
}

type ContaPoupansa struct {
	Titular       titular.Titular
	NumeroAgencia int
	NumeroConta   int
	saldo         float64
}

func (c *ContaCorrente) Depositar(valor float64) (bool, string) {
	if valor > 0 {
		c.saldo += valor
		return true, "Depósito realizado com sucesso"
	}
	return false, "Valor inválido para depósito"
}

func (c *ContaCorrente) Sacar(valor float64) (bool, string, float64) {
	if c.saldo >= valor {
		c.saldo -= valor
		return true, "Saque realizado com sucesso", valor
	}
	return false, "Saldo insuficiente para realizar o saque", 0
}

func (c *ContaCorrente) ObterSaldo() float64 {
	return c.saldo
}

func (c *ContaPoupansa) Depositar(valor float64) (bool, string) {
	if valor > 0 {
		c.saldo += valor
		return true, "Depósito realizado com sucesso"
	}
	return false, "Valor inválido para depósito"
}

func (c *ContaPoupansa) Sacar(valor float64) (bool, string, float64) {
	if c.saldo >= valor {
		c.saldo -= valor
		return true, "Saque realizado com sucesso", valor
	}
	return false, "Saldo insuficiente para realizar o saque", 0
}

func (c *ContaPoupansa) ObterSaldo() float64 {
	return c.saldo
}
