package utils

import (
	"strconv"
)

func FloatValue(stringValue string) float64 {
	value, err := strconv.ParseFloat(stringValue, 64)
	if err != nil {
		panic(err)
	}
	return value
}

func IntValue(stringValue string) int {
	value, err := strconv.Atoi(stringValue)
	if err != nil {
		panic(err)
	}
	return value
}
