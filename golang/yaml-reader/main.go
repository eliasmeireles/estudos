package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"os"

	"github.com/atotto/clipboard"
	"gopkg.in/yaml.v2"
)

func (eE EnvEntry) ToString() string {
	return "-D" + eE.Name + "=" + eE.Value + "\n"
}

func (env Env) ToString() string {
	output := ""
	for _, entry := range env.Values {
		output += entry.ToString()
	}
	return output
}

type EnvEntry struct {
	Name  string `yaml:"name"`
	Value string `yaml:"value"`
}

type Env struct {
	Values []EnvEntry `yaml:"env"`
}

func (env *Env) loadYamlEnv() *Env {
	dir, err := os.Getwd()
	if err != nil {
		log.Fatal(err)
	}
	yamlFile, err := ioutil.ReadFile(dir + "/values.yaml")

	if err != nil {
		log.Printf("%v", err)
		os.Exit(1)
	}
	err = yaml.Unmarshal(yamlFile, env)
	if err != nil {
		log.Fatalf("Unmarshal: %v", err)
	}
	return env
}

func main() {
	var env Env
	env.loadYamlEnv()

	err := clipboard.WriteAll(env.ToString())
	if err != nil {
		fmt.Println("")
		fmt.Println(env.ToString())
		fmt.Println("Failed to set data do clipboard:", err)
		os.Exit(1)
	} else {
		fmt.Println("")
		fmt.Println("Paste clipboard values is ready")
		fmt.Println("")
		fmt.Println(env.ToString())
	}
}
