package main

import (
	"bufio"
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"os"
	"strconv"
	"strings"
	"time"
)

const monitoramentoSleep = 5

type Executor interface {
	run()
	key() int
}

type Logs struct {
}

type ComandoDesconhecido struct {
}

type Monitoramento struct {
}

type Finalizar struct {
}

func (_ Finalizar) key() int {
	return 0
}

func (_ Logs) key() int {
	return 2
}

func (_ Monitoramento) key() int {
	return 1
}

func (_ ComandoDesconhecido) key() int {
	return -1
}

var finalizar = Finalizar{}
var monitoramento = Monitoramento{}
var defaultExecutor = ComandoDesconhecido{}
var logs = Logs{}

func main() {
	inicializar()
}

func obterComando(opcao int) Executor {
	executores := []Executor{finalizar, monitoramento, logs}
	for _, executor := range executores {
		if executor.key() == opcao {
			return executor
		}
	}
	return defaultExecutor
}

func executar(opcao int) {
	fmt.Println("")
	obterComando(opcao).run()
}

func inicializar() {
	exibeIntro()
	menuOption()
	opcaoSelecionada := selecionarItemDoMenu()
	executar(opcaoSelecionada)
	inicializar()
}

func selecionarItemDoMenu() int {
	var comando int
	_, err := fmt.Scan(&comando)
	if err != nil {
		fmt.Println("Não foi possível executar a operação com o valor informado. Tente novamente")
		os.Exit(1)
	}
	fmt.Println("O comando escolhido foi", comando)
	return comando
}

func menuOption() {
	fmt.Println(monitoramento.key(), "- Iniciar Monitoramento")
	fmt.Println(logs.key(), "- Exibir Logs")
	fmt.Println(finalizar.key(), "- Sair do Programa")

}

func exibeIntro() {
	nome := "Elias"
	versao := 1.1

	fmt.Println("Olá, sr.", nome)
	fmt.Println("Este programa está na versão", versao)
}

func (ComandoDesconhecido) run() {
	fmt.Println("Não conheço este comando")
}

func (_ Logs) run() {
	fmt.Println("Exibindo logs...")
	fmt.Println("")
	imprirLogs()
}

func (_ Finalizar) run() {
	fmt.Println("Saindo do programa...")
	os.Exit(0)
}

func (_ Monitoramento) run() {
	fmt.Println("Monitorando...")

	//sites := []string{
	//	"https://random-status-code.herokuapp.com",
	//	"https://www.alura.com",
	//	"https://www.caelum.com",
	//}
	sites := lerSitesDoArquivo()

	for i := 0; i < monitoramentoSleep; i++ {
		for _, site := range sites {
			testaSite(site)
		}
		time.Sleep(5 * time.Second)
	}
	fmt.Println("")
}

func testaSite(site string) {
	response, err := http.Get(site)
	if err == nil {
		if response.StatusCode == 200 {
			fmt.Println("Site", site, "foi carregado com sucesso!")
			registraLog(site, true)
		} else {
			fmt.Println("Site", site, "está com problemas. Status Code: ", response.StatusCode)
			registraLog(site, false)
		}
	} else {
		fmt.Println("Falha ao testar o site:", site, "erro:", err.Error())
		registraLog(err.Error(), false)

	}
}

func lerSitesDoArquivo() []string {
	arquivo, err := os.Open("sites.txt")
	//arquivo, err := ioutil.ReadFile("sites.txt")
	var sites []string

	if err != nil {
		fmt.Println("Falha no carregamento do arquivo de sites, erro:", err)
	} else {
		sites = lerLinhasDoArquivo(arquivo)
		//goland:noinspection GoUnhandledErrorResult
		arquivo.Close()
	}

	return sites
}

func lerLinhasDoArquivo(arquivo *os.File) []string {
	leitor := bufio.NewReader(arquivo)

	var sites []string
	for {
		linha, err := leitor.ReadString('\n')
		sites = append(sites, strings.TrimSpace(linha))

		if err == io.EOF {
			break
		}
	}
	return sites
}

//goland:noinspection GoUnhandledErrorResult
func registraLog(site string, status bool) {
	exists, dirCheckErr := exists("./log")
	if !exists {
		os.Mkdir("log", 0777)
	} else if dirCheckErr != nil {
		fmt.Println("Falha ao registrar log:", dirCheckErr)
		return
	}

	arquivo, err := os.OpenFile("./log/log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		fmt.Println("Falha ao registrar log:", err)
	} else {
		arquivo.WriteString(time.Now().Format("02/01/2006 15:04:05") + " - " + site + " - online: " + strconv.FormatBool(status) + "\n")
		arquivo.Close()
	}
}

// exists returns whether the given file or directory exists
func exists(path string) (bool, error) {
	_, err := os.Stat(path)
	if err == nil {
		return true, nil
	}
	if os.IsNotExist(err) {
		return false, nil
	}
	return false, err
}

func imprirLogs() {
	arquivo, err := ioutil.ReadFile("log/log.txt")

	if err != nil {
		fmt.Println("Falha no carregamento dos logs, erro:", err)
	} else {
		fmt.Println(string(arquivo))
	}
}
