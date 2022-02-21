# Go

- Update dependencies `go mod tidy`

## [Environment variables in Golang](https://www.loginradius.com/blog/async/environment-variables-in-golang/)

````go
package main

import (
	"fmt"
	"os"
)

// working with system env
func main() {
	// Set Environment Variables
	os.Setenv("SITE_TITLE", "Test Site")
	os.Setenv("DB_HOST", "localhost")
	os.Setenv("DB_PORT", "27017")
	os.Setenv("DB_USERNAME", "admin")
	os.Setenv("DB_PASSWORD", "password")
	os.Setenv("DB_NAME", "testdb")

	// Get the value of an Environment Variable
	host := os.Getenv("SITE_TITLE")
	port := os.Getenv("DB_HOST")
	fmt.Printf("Site Title: %s, Host: %s\n", host, port)

	// Unset an Environment Variable
	os.Unsetenv("SITE_TITLE")
	fmt.Printf("After unset, Site Title: %s\n", os.Getenv("SITE_TITLE"))

	//Checking that an environment variable is present or not.
	redisHost, ok := os.LookupEnv("REDIS_HOST")
	if !ok {
		fmt.Println("REDIS_HOST is not present")
	} else {
		fmt.Printf("Redis Host: %s\n", redisHost)
	}

	// Expand a string containing environment variables in the form of $var or ${var}
	dbURL := os.ExpandEnv("mongodb://${DB_USERNAME}:${DB_PASSWORD}@$DB_HOST:$DB_PORT/$DB_NAME")
	fmt.Println("DB URL: ", dbURL)
}
````

## Check if file or directory exists

````go
package main

import "os"

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
````

## [YAML support for the Go language](https://pkg.go.dev/gopkg.in/yaml.v3)

````go
package main

import (
	"fmt"
	"io/ioutil"
	"log"

	"gopkg.in/yaml.v2"
)

type conf struct {
	Hits int64 `yaml:"hits"`
	Time int64 `yaml:"time"`
}

func (c *conf) getConf() *conf {

	yamlFile, err := ioutil.ReadFile("conf.yaml")
	if err != nil {
		log.Printf("yamlFile.Get err   #%v ", err)
	}
	err = yaml.Unmarshal(yamlFile, c)
	if err != nil {
		log.Fatalf("Unmarshal: %v", err)
	}

	return c
}

func main() {
	var c conf
	c.getConf()

	fmt.Println(c)
}

````

