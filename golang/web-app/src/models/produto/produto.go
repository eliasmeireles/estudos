package produto

type Produto struct {
	ProdutoId  int
	Nome       string
	Descricao  string
	Preco      float64
	Quantidade int
}

func (p Produto) GetInsertValues() (string, string, float64, int) {
	return p.Nome, p.Descricao, p.Preco, p.Quantidade
}

func (p Produto) GetUpdateValues() (int, string, string, float64, int) {
	return p.ProdutoId, p.Nome, p.Descricao, p.Preco, p.Quantidade
}
