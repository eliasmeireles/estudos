package routes

import (
	alunosController "gin-api-rest/controllers"
	"github.com/gin-gonic/gin"
)

func alunosRequests(r *gin.Engine) {
	r.GET(alunosController.AlunosPath, alunosController.ExibeTodosOsAlunos)
	r.GET(alunosController.AlunoPathId, alunosController.BuscaAlunoPorId)
	r.GET(alunosController.AlunoPathCpf, alunosController.BuscaAlunoPorCpf)
	r.DELETE(alunosController.AlunoPathId, alunosController.DeletaAluno)
	r.PUT(alunosController.AlunoPathId, alunosController.EditarAluno)
	r.POST(alunosController.AlunosPath, alunosController.CriaNovoAluno)
}
