package controllers

import (
	"encoding/json"
	"net/http"
)

func ResponseWriteOk(w http.ResponseWriter, response interface{}) error {
	w.WriteHeader(http.StatusOK)
	encoderErr := json.NewEncoder(w).Encode(response)
	return encoderErr
}

func ResponseWriteCreated(w http.ResponseWriter, response interface{}) error {
	w.WriteHeader(http.StatusCreated)
	encoderErr := json.NewEncoder(w).Encode(response)
	return encoderErr
}
