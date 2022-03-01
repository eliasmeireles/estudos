package routes

import (
	"github.com/gin-gonic/gin"
)

func HandleRequests() {
	var engine = gin.Default()
	alunosRequests(engine)
	serverRun(engine)
}

func serverRun(r *gin.Engine) {
	err := r.Run(":8000")
	if err != nil {
		panic(err.Error())
	}
}
