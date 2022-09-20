#!/usr/bin/python3

from random import randint

value = randint(0, 100)

def rangeValidation(input):
    rangeResult = abs(value - input)
    if rangeResult <= 10:
        print("Está esquentando...")
    else:
        print("Está frio...")
    return False

def inputValidation(input):
    if input != value:
       return rangeValidation(input) 
    else:
        print("Você ganhou!!!!")
    return True

def play(tryCount):
    inputValue = int(input("Informe um número entre 0 à 100: "))
    if inputValidation(inputValue) == False:
       if tryCount == 5:
           print("Você perdeu!")
           print("Valor esperado é: " + str(value)) 
       else:
           play(tryCount + 1) 

play(0)