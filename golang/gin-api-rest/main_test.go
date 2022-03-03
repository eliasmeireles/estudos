package main

import (
	"gin-api-rest/routes"
	"github.com/stretchr/testify/assert"
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"testing"
)

func TestVerificaStatusCodeDaSaudacaoComParametro(t *testing.T) {
	r := routes.HandleRequests()
	req, _ := http.NewRequest(http.MethodGet, "/elias", nil)
	resposta := httptest.NewRecorder()
	mockResposta := `{"API diz":"E ai elias, tudo beleza?"}`
	r.ServeHTTP(resposta, req)
	responseBody, _ := ioutil.ReadAll(resposta.Body)

	assert.Equal(t, http.StatusOK, resposta.Code)
	assert.Equal(t, mockResposta, string(responseBody))
}
