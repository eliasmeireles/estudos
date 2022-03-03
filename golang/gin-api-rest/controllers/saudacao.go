package controllers

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
)

func Suadacao(c *gin.Context) {
	nome := c.Param("nome")
	c.JSONP(http.StatusOK, gin.H{
		"API diz": fmt.Sprintf("E ai %s, tudo beleza?", nome),
	})
}
