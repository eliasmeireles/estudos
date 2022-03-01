package database

import (
	"fmt"
	"gin-api-rest/models"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"log"
)

var (
	DB  *gorm.DB
	err error
)

func ConectaComBancoDeDados() {
	fmt.Println("Inicializando conexão com banco de dados")
	dbConexao := "host=127.0.0.1 user=golangAlura password=golangAlura dbname=postgres port=5435 sslmode=disable"
	DB, err = gorm.Open(postgres.Open(dbConexao), &gorm.Config{})

	if err != nil {
		log.Panicln("Erro ao conectar com banco de dados")
	} else {
		alunoMigration()
		fmt.Println("Conexão com banco realizada com sucesso")
	}
}

func alunoMigration() {
	alunoMigrationError := DB.AutoMigrate(&models.Aluno{})
	if alunoMigrationError != nil {
		log.Panicln(alunoMigrationError.Error())
	}
}
