package database

import (
	"database/sql"
	"fmt"

	_ "github.com/lib/pq"
)

func ConectaComBancoDeDados() *sql.DB {
	conexao := "user=golangAlura password=golangAlura host=127.0.0.1 port=5434 sslmode=disable dbname=postgres"
	db, err := sql.Open("postgres", conexao)
	if err != nil {
		panic(err.Error())
	}
	fmt.Println("Conexão realizada com sucesso")
	return db
}

func FecharConexaoComBancoDeDados(db *sql.DB) {
	err := db.Close()
	if err != nil {
		panic(err.Error())
	}
	fmt.Println("Conexão com o banco de dados finalizada com sucesso")
}
