package main

import (
	"api-rest/database"
	"api-rest/routes"
	"fmt"
)

func main() {
	fmt.Println("Iniciando o servidor Rest com Go")
	database.ConectaComBancoDeDados()
	routes.HandleResponse()
}
