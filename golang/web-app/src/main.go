package main

import (
	_ "github.com/lib/pq"
	"net/http"
	"web-app/src/routes"
)

func main() {
	routes.CarregarRotas()
	err := http.ListenAndServe(":8000", nil)
	if err != nil {
		return
	}
}
