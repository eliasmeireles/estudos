package controllers

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func RotaNaoEncontrada(c *gin.Context) {
	c.HTML(http.StatusNotFound, "404.html", nil)
}
