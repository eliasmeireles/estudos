package routes

import (
	"api-rest/controllers"
	"api-rest/middleware"
	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

func HandleResponse() {
	r := mux.NewRouter()
	r.Use(middleware.ContentTypeMiddleware)
	r.HandleFunc("/", controllers.Home)
	r.HandleFunc("/api/personalidades", controllers.TodasPersonalidades).Methods(http.MethodGet)
	r.HandleFunc("/api/personalidades/{id}", controllers.RetornaUmaPersonalidade).Methods(http.MethodGet)
	r.HandleFunc("/api/personalidades/{id}", controllers.DeletaUmPersonalidade).Methods(http.MethodDelete)
	r.HandleFunc("/api/personalidades/{id}", controllers.EditaNovaPersonalidade).Methods(http.MethodPut)
	r.HandleFunc("/api/personalidades", controllers.CriarUmaNovaPersonalidade).Methods(http.MethodPost)
	log.Fatalln(http.ListenAndServe(":8000", corsHandler()(r)))
}

func corsHandler() func(http.Handler) http.Handler {
	return handlers.CORS(handlers.AllowedOrigins([]string{"*"}))
}
