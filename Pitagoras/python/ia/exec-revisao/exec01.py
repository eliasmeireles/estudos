import datetime
import locale
import string


print("""\n\n
[Exec 01]

Crie um algoritmo que receba um número, conte o número total de dígitos e mostre o resultado.
Por exemplo, se o número é 2021, então a saída deve ser 4.
""")

valInput = input("Informe um número: ")
print("O total de dígitos é: " + str(len(valInput)))

print("""\n\n
[Exec 02]

Faça um programa que receba um número digitado pelo usuário e calcule a soma de todos os números de 1 até ao número digitado.
Por exemplo, se o usuário digitou o número 4, a saída deve ser 10 (1+2+3+4=10).
""")

outPut=int(valInput)
stringOutCount=""
for i in range(1, int(valInput)):
    stringOutCount += "+" + str(i)
    outPut += i


stringOutCount += "+" + valInput

print(stringOutCount.replace("+", "", 1) + "=" + str(outPut))
print("O valor de saída é: " + str(outPut))

print("""\n\n
[Exec 03]

Escrever um algoritmo que leia um valor para uma variável N de 1 a 10 e faça o algoritmo
com duas estruturas de repetição distintas. Mostre a tabuada na forma: 0 x N = 0, 1 x N = 1N, 2 x N = 2N, ..., 10 x N = 10N.
""")


inputValue02 = int(input("Infome um número de 1 até 10: "))
while inputValue02 < 1 or inputValue02 > 10:
    inputValue02 = int(input("Infome um número de 1 até 10: "))

stringOut=""

for i in range(11):
    stringOut += str(i) +  " x " +  str(inputValue02) + " = "+ str(i * inputValue02) + ", "

print(stringOut[:(len(stringOut) - 2)])

print("""
\n\n
[Exec 04]

Faça um programa que receba a temperatura média de cada mês do ano e armazene-as
em um dicionário. Após isto, crie uma função que calcule a média anual das temperaturas
e mostre todas as temperaturas acima da média anual, e em que mês elas ocorreram (mostrar o
mês por extenso: 1Janeiro, 2 Fevereiro.""")


mesesDoAno = {}

def getTemp(mes: string):
    temp = float(input("Informe a temperatura para o mês de " + mes + ": "))
    return temp

locale.setlocale(locale.LC_ALL, 'pt_BR.UTF-8')

tempTotal = 0.0
for i in range(1, 13):
    nomeMes = datetime.datetime(2021, i, 1).strftime("%B")
    temp = getTemp(nomeMes)
    tempTotal += temp
    mesesDoAno[i] = {"nome": nomeMes, "temp": temp}

tempMedia = tempTotal / len(mesesDoAno)
print("\nA média da temperatura anual é de: ", str(tempMedia))
print("\nTemperaturas acima da média")

for temp in mesesDoAno.values():
    if temp["temp"] > tempMedia:
        print(temp["nome"], "temperatura: ", temp["temp"])
