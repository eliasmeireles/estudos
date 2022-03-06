package routes

import (
	"bytes"
	"encoding/json"
	"fmt"
	"gin-api-rest/controllers"
	"gin-api-rest/database"
	"gin-api-rest/models"
	"github.com/stretchr/testify/assert"
	"net/http"
	"net/http/httptest"
	"testing"
)

func CriaAlunoMock() models.Aluno {
	aluno := models.Aluno{
		Nome: "Nome do Aluno Teste",
		CPF:  "108.084.467-85",
		RG:   "RJ 184557569",
	}
	database.DB.Create(&aluno)
	return aluno
}

func DeletarAlunoMock(aluno models.Aluno) {
	database.DB.Delete(&aluno, aluno.ID)
}

func TestListandoTodosOsAlunosHandler(t *testing.T) {
	database.ConectaComBancoDeDados()
	alunoMock := CriaAlunoMock()
	defer DeletarAlunoMock(alunoMock)
	r := HandleRequests()
	req, _ := http.NewRequest(http.MethodGet, controllers.AlunosPath, nil)
	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)

	assert.Equal(t, http.StatusOK, resposta.Code)
}

func TestBuscaAlunoPorCpf(t *testing.T) {
	database.ConectaComBancoDeDados()
	alunoMock := CriaAlunoMock()
	defer DeletarAlunoMock(alunoMock)
	r := HandleRequests()
	req, _ := http.NewRequest(http.MethodGet, fmt.Sprintf("%s/%s/%s",
		controllers.AlunosPath,
		controllers.AlunoCpf,
		alunoMock.CPF), nil)

	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)

	assert.Equal(t, http.StatusOK, resposta.Code)
}

func TestBuscaAlunoPorId(t *testing.T) {
	database.ConectaComBancoDeDados()
	alunoMock := CriaAlunoMock()
	defer DeletarAlunoMock(alunoMock)
	r := HandleRequests()
	req, _ := http.NewRequest(http.MethodGet, fmt.Sprintf("%s/%d",
		controllers.AlunosPath,
		alunoMock.ID), nil)

	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)

	var expectedAlunoMock models.Aluno
	err := json.Unmarshal(resposta.Body.Bytes(), &expectedAlunoMock)

	assert.Equal(t, nil, err)
	assert.Equal(t, alunoMock.Nome, expectedAlunoMock.Nome, "O nome do esperado é diferente do nome do aluno atual")
	assert.Equal(t, alunoMock.CPF, expectedAlunoMock.CPF, "O CPF do esperado é diferente do CPF do aluno atual")
	assert.Equal(t, alunoMock.RG, expectedAlunoMock.RG, "O RG do esperado é diferente do RG do aluno atual")
}

func TestDeletaAluno(t *testing.T) {
	database.ConectaComBancoDeDados()
	alunoMock := CriaAlunoMock()

	r := HandleRequests()
	req, _ := http.NewRequest(http.MethodDelete, fmt.Sprintf("%s/%d",
		controllers.AlunosPath,
		alunoMock.ID), nil)

	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)

	assert.Equal(t, http.StatusOK, resposta.Code)

	reqValidaAlunoDeletado, _ := http.NewRequest(http.MethodGet, fmt.Sprintf("%s/%d",
		controllers.AlunosPath,
		alunoMock.ID), nil)

	respostaNotFound := httptest.NewRecorder()
	r.ServeHTTP(respostaNotFound, reqValidaAlunoDeletado)

	assert.Equal(t, http.StatusNotFound, respostaNotFound.Code)
}

func TestEditaAlunoHandler(t *testing.T) {
	database.ConectaComBancoDeDados()
	alunoMock := CriaAlunoMock()
	defer DeletarAlunoMock(alunoMock)
	r := HandleRequests()

	alunoAtualizado := models.Aluno{
		Nome: "Nome do Aluno Atualizado",
		CPF:  "108.084.467-86",
		RG:   "RJ 184557563",
	}
	alunoJson, err := json.Marshal(alunoAtualizado)
	assert.Equal(t, nil, err)

	req, _ := http.NewRequest(http.MethodPut, fmt.Sprintf("%s/%d",
		controllers.AlunosPath,
		alunoMock.ID), bytes.NewBuffer(alunoJson))

	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)

	var expectedAlunoAtualizado models.Aluno
	err = json.Unmarshal(resposta.Body.Bytes(), &expectedAlunoAtualizado)
	assert.Equal(t, nil, err)

	assert.Equal(t, alunoAtualizado.Nome, expectedAlunoAtualizado.Nome, "O nome do esperado é diferente do nome do aluno atual")
	assert.Equal(t, alunoAtualizado.CPF, expectedAlunoAtualizado.CPF, "O CPF do esperado é diferente do CPF do aluno atual")
	assert.Equal(t, alunoAtualizado.RG, expectedAlunoAtualizado.RG, "O RG do esperado é diferente do RG do aluno atual")
}
