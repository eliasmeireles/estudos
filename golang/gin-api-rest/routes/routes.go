package routes

import (
	"gin-api-rest/controllers"
	"github.com/gin-gonic/gin"
)

func HandleRequests() *gin.Engine {
	var engine = gin.Default()
	engine.GET("/:nome", controllers.Suadacao)
	alunosRequests(engine)
	return engine
}
