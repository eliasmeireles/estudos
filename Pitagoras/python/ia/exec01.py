endereco = {
    "Rua": "Uma rua sem nome",
    "Numero": "1234AD"
}

usuario = {
    "Nome": "Elias Meireles",
    "Utimo Nome": "Ferreira",
    "Idade": "34",
    "Curso": "Fundamento IA",
    "Endereco": endereco
}

print(usuario)
print(usuario["Nome"])
print(usuario["Utimo Nome"])
print(usuario["Idade"])
print(usuario["Curso"])
print(usuario["Endereco"]["Rua"])
print(usuario["Endereco"]["Numero"])

del usuario["Curso"]
usuario["Utimo Nome"] = "Lopes"

print(usuario)

print(usuario["Endereco"])
usuarioCopia = usuario
usuarioCopia["Nome"] = "Fernandes"
usuarioCopia["Idade"] = 25
print(usuarioCopia)



