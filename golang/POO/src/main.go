package main

import (
	"fmt"
	"src/models/contas/src/models/contas"
	"src/models/contas/src/models/titular"
)

func pagarBoleto(conta contas.PagarBoleto, valor float64) string {
	saqueRealizado, erroMessage, _ := conta.Sacar(valor)
	if saqueRealizado {
		return fmt.Sprint("Boleto com valor de R$ ", valor, " pago com sucesso")
	}
	return "Falha no pagamento, erro: " + erroMessage
}

func main() {
	titular := titular.Titular{Nome: "Elias Meireles", CPF: "106.058.457-95"}
	contaElias := contas.ContaCorrente{Titular: titular, NumeroAgencia: 54879, NumeroConta: 25893}
	_, message := contaElias.Depositar(1000)
	fmt.Println(message)
	fmt.Println(contaElias)
	_, messages := contaElias.Depositar(-1000)
	fmt.Println(messages)
	fmt.Println(contaElias)

	pagarBoletoMessage := pagarBoleto(&contaElias, 7555.98)
	fmt.Println(pagarBoletoMessage)

}
