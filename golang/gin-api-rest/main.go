package main

import (
	"gin-api-rest/database"
	"gin-api-rest/routes"
	"github.com/gin-gonic/gin"
)

func main() {
	database.ConectaComBancoDeDados()
	engine := routes.HandleRequests()
	serverRun(engine)
}

func serverRun(r *gin.Engine) {
	err := r.Run(":8000")
	if err != nil {
		panic(err.Error())
	}
}
