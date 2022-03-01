package controllers

import (
	"gin-api-rest/database"
	"gin-api-rest/models"
	"github.com/gin-gonic/gin"
	"net/http"
)

const (
	AlunosPath   = "/alunos"
	AlunoId      = "id"
	AlunoCpf     = "cpf"
	AlunoPathId  = AlunosPath + "/:" + AlunoId
	AlunoPathCpf = AlunosPath + "/cpf/:" + AlunoCpf
)

func ExibeTodosOsAlunos(c *gin.Context) {
	var alunos []models.Aluno
	database.DB.Find(&alunos)
	c.JSONP(http.StatusOK, alunos)
}

func BuscaAlunoPorId(c *gin.Context) {
	var aluno models.Aluno
	id := c.Param(AlunoId)
	database.DB.First(&aluno, id)
	onQueryResponseHandler(c, aluno)
}

func BuscaAlunoPorCpf(c *gin.Context) {
	var aluno models.Aluno
	cpf := c.Param(AlunoCpf)
	database.DB.Where(&models.Aluno{CPF: cpf}).Find(&aluno)
	onQueryResponseHandler(c, aluno)
}

func onQueryResponseHandler(c *gin.Context, aluno models.Aluno) {
	if aluno.ID == 0 {
		c.JSONP(http.StatusNotFound, gin.H{
			"Not found": "Aluno não encontrado",
		})
	} else {
		c.JSONP(http.StatusOK, aluno)
	}
}

func DeletaAluno(c *gin.Context) {
	var aluno models.Aluno
	id := c.Param(AlunoId)
	database.DB.First(&aluno, id)
	database.DB.Delete(&aluno, id)
	c.JSONP(http.StatusOK, gin.H{
		"Aluno deletado": "Aluno deletado com sucesso",
	})
}

func CriaNovoAluno(c *gin.Context) {
	var aluno models.Aluno
	if err := c.ShouldBindJSON(&aluno); err != nil {
		c.JSONP(http.StatusBadRequest, gin.H{
			"error": err.Error(),
		})
	}
	database.DB.Create(&aluno)
	c.JSONP(http.StatusCreated, aluno)
}

func EditarAluno(c *gin.Context) {
	var aluno models.Aluno
	id := c.Param(AlunoId)
	database.DB.First(&aluno, id)
	if aluno.ID == 0 {
		c.JSONP(http.StatusNotFound, gin.H{
			"Not found": "Aluno não encontrado",
		})
		return
	}

	if err := c.ShouldBindJSON(&aluno); err != nil {
		c.JSONP(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	database.DB.Save(&aluno)
	c.JSONP(http.StatusOK, aluno)
}
