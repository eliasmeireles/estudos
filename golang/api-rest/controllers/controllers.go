package controllers

import (
	"api-rest/database"
	"api-rest/models"
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"net/http"
)

func Home(w http.ResponseWriter, _ *http.Request) {
	_, err := fmt.Fprintln(w, "Home Page")
	if err != nil {
		panic(err.Error())
	}
}

func TodasPersonalidades(w http.ResponseWriter, _ *http.Request) {
	var p []models.Personalidade
	database.DB.Find(&p)

	encoderErr := ResponseWriteOk(w, p)
	if encoderErr != nil {
		panic(encoderErr.Error())
	}
}

func RetornaUmaPersonalidade(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)

	personalidadeId := vars["id"]

	var p models.Personalidade
	database.DB.First(&p, personalidadeId)

	encoderErr := ResponseWriteOk(w, p)
	if encoderErr != nil {
		panic(encoderErr.Error())
	}
}

func CriarUmaNovaPersonalidade(w http.ResponseWriter, r *http.Request) {
	var novaPersonalidade models.Personalidade
	err := json.NewDecoder(r.Body).Decode(&novaPersonalidade)
	if err != nil {
		panic(err.Error())
	}
	database.DB.Create(&novaPersonalidade)
	encoderErr := ResponseWriteCreated(w, novaPersonalidade)
	if encoderErr != nil {
		panic(encoderErr.Error())
	}
}

func EditaNovaPersonalidade(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	personalidadeId := vars["id"]

	var personalidade models.Personalidade
	database.DB.First(&personalidade, personalidadeId)
	decoderErr := json.NewDecoder(r.Body).Decode(&personalidade)
	if decoderErr != nil {
		panic(decoderErr.Error())
	}

	database.DB.Save(&personalidade)

	encoderErr := ResponseWriteOk(w, personalidade)
	if encoderErr != nil {
		panic(encoderErr.Error())
	}
}

func DeletaUmPersonalidade(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)

	personalidadeId := vars["id"]

	var personalidade models.Personalidade
	database.DB.Delete(&personalidade, personalidadeId)
	response := models.BaseResponse{Success: true, Message: "Personalidade deletada com sucesso"}
	encoderErr := ResponseWriteOk(w, response)
	if encoderErr != nil {
		panic(encoderErr.Error())
	}
}
