package controllers

import (
	"net/http"
	"text/template"
	pDb "web-app/src/database/produtos"
	p "web-app/src/models/produto"
	"web-app/src/utils"
)

var temp = template.Must(template.ParseGlob("src/templates/*.html"))

func Index(responseWriter http.ResponseWriter, request *http.Request) {
	todosOsProdutos := pDb.BuscarTodosOsProduto()
	execute(responseWriter, "Index", todosOsProdutos)
}

func BuscarProduto(responseWriter http.ResponseWriter, request *http.Request) {
	idProduto := utils.IntValue(request.URL.Query().Get("produtoId"))
	produto := pDb.BuscarProduto(idProduto)
	execute(responseWriter, "Edit", produto)
}

func New(responseWriter http.ResponseWriter, request *http.Request) {
	execute(responseWriter, "New", nil)
}

func Insert(responseWriter http.ResponseWriter, request *http.Request) {
	if request.Method == "POST" {
		produto := p.Produto{
			Nome:       request.FormValue("nome"),
			Descricao:  request.FormValue("descricao"),
			Quantidade: utils.IntValue(request.FormValue("quantidade")),
			Preco:      utils.FloatValue(request.FormValue("preco")),
		}
		pDb.CriaNovoProduto(produto)
	}
	http.Redirect(responseWriter, request, "/", 301)
}

func Edit(responseWriter http.ResponseWriter, request *http.Request) {
	if request.Method == "POST" {
		produto := p.Produto{
			ProdutoId:  utils.IntValue(request.FormValue("produtoId")),
			Nome:       request.FormValue("nome"),
			Descricao:  request.FormValue("descricao"),
			Quantidade: utils.IntValue(request.FormValue("quantidade")),
			Preco:      utils.FloatValue(request.FormValue("preco")),
		}
		pDb.EditaProduto(produto)
	}
	http.Redirect(responseWriter, request, "/", 301)
}

func Delete(responseWriter http.ResponseWriter, request *http.Request) {
	idProduto := utils.IntValue(request.URL.Query().Get("produtoId"))
	pDb.DeletaProduto(idProduto)
	http.Redirect(responseWriter, request, "/", 301)
}

func execute(responseWriter http.ResponseWriter, name string, data interface{}) {
	err := temp.ExecuteTemplate(responseWriter, name, data)
	if err != nil {
		panic(err.Error())
	}
}
