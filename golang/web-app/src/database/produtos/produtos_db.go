package produtos

import (
	"database/sql"
	"fmt"
	"web-app/src/database"
	"web-app/src/models/produto"
)

func BuscarTodosOsProduto() []produto.Produto {
	db := database.ConectaComBancoDeDados()
	selectTodosOsProdutos, err := db.Query("SELECT * FROM produtos ORDER BY produto_id")
	if err != nil {
		panic(err.Error())
	}

	var produtos []produto.Produto

	for selectTodosOsProdutos.Next() {
		produtoRow := carregarProduto(selectTodosOsProdutos)
		produtos = append(produtos, produtoRow)
	}
	defer database.FecharConexaoComBancoDeDados(db)
	return produtos
}

func carregarProduto(sqlRows *sql.Rows) produto.Produto {
	var produtoId, quantidade int
	var nome, descricacao string
	var preco float64

	err := sqlRows.Scan(&produtoId, &nome, &descricacao, &preco, &quantidade)

	if err != nil {
		panic(err.Error())
	}

	return produto.Produto{
		ProdutoId:  produtoId,
		Nome:       nome,
		Descricao:  descricacao,
		Preco:      preco,
		Quantidade: quantidade,
	}
}

func BuscarProduto(produtoId int) produto.Produto {
	bancoDeDados := database.ConectaComBancoDeDados()
	selectProdutoQuery, err := bancoDeDados.Query("SELECT * FROM produtos WHERE produto_id = $1", produtoId)
	if err != nil {
		panic(err.Error())
	}
	if selectProdutoQuery.Next() {
		produtoSelecionado := carregarProduto(selectProdutoQuery)
		defer database.FecharConexaoComBancoDeDados(bancoDeDados)
		return produtoSelecionado
	} else {
		defer database.FecharConexaoComBancoDeDados(bancoDeDados)
		panic("Produto não encontrado")
	}
}

func CriaNovoProduto(novoProduto produto.Produto) {
	bancoDeDados := database.ConectaComBancoDeDados()
	stmt, err := bancoDeDados.Prepare("INSERT INTO produtos (nome, descricao, preco, quantidade) VALUES ($1, $2, $3, $4)")
	if err != nil {
		panic(err.Error())
	}
	_, errExec := stmt.Exec(novoProduto.GetInsertValues())
	if errExec != nil {
		panic(err.Error())
	}
	fmt.Println("Produto salvo com sucesso")
	defer database.FecharConexaoComBancoDeDados(bancoDeDados)
}

func EditaProduto(novoProduto produto.Produto) {
	bancoDeDados := database.ConectaComBancoDeDados()
	stmt, err := bancoDeDados.Prepare("UPDATE produtos SET nome = $2, descricao = $3, preco = $4, quantidade = $5 WHERE produto_id = $1")
	if err != nil {
		panic(err.Error())
	}
	_, errExec := stmt.Exec(novoProduto.GetUpdateValues())
	if errExec != nil {
		panic(err.Error())
	}
	fmt.Println("Produto editado com sucesso")
	defer database.FecharConexaoComBancoDeDados(bancoDeDados)
}

func DeletaProduto(produtoId int) {
	bancoDeDados := database.ConectaComBancoDeDados()
	stmt, err := bancoDeDados.Prepare("DELETE FROM produtos WHERE produto_id = $1")
	if err != nil {
		panic(err.Error())
	}
	_, errExec := stmt.Exec(produtoId)
	if errExec != nil {
		panic(err.Error())
	}
	fmt.Println("Produto deletado com sucesso")
	defer database.FecharConexaoComBancoDeDados(bancoDeDados)
}
