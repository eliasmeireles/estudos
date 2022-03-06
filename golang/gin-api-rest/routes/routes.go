package routes

import (
	"gin-api-rest/controllers"
	"github.com/gin-gonic/gin"
)

func HandleRequests() *gin.Engine {
	gin.SetMode(gin.ReleaseMode)
	var engine = gin.Default()
	engine.LoadHTMLGlob("templates/*")
	engine.Static("/assets", "./assets")
	engine.NoRoute(controllers.RotaNaoEncontrada)
	engine.GET("/:nome", controllers.Suadacao)
	alunosRequests(engine)
	return engine
}
