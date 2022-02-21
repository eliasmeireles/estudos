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
	yamlFile, err := ioutil.ReadFile(yamlFilePath())
	if err != nil {
		log.Printf("yamlFile.Get err   #%v ", err)
	}
	err = yaml.Unmarshal(yamlFile, env)
	if err != nil {
		log.Fatalf("Unmarshal: %v", err)
	}
	return env
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

func yamlFilePath() string {
	dir, err := os.Getwd()
	if err != nil {
		log.Fatal(err)
	}
	return dir + "/values.yaml"
}

func getArgs() {
	args := os.Args
	fmt.Println(args[0])

	for position, arg := range args {
		if arg == "-p" {
			path := args[position+1]
			fmt.Println(path)
		}
	}
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
		fmt.Println("Env loaded successful")
		fmt.Println(env.ToString())
	}
}
