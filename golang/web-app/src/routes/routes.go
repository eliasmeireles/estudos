package routes

import (
	"net/http"
	"web-app/src/controllers"
)

func CarregarRotas() {
	http.HandleFunc("/", controllers.Index)
	http.HandleFunc("/new", controllers.New)
	http.HandleFunc("/insert", controllers.Insert)
	http.HandleFunc("/editar", controllers.BuscarProduto)
	http.HandleFunc("/update", controllers.Edit)
	http.HandleFunc("/delete", controllers.Delete)
}
