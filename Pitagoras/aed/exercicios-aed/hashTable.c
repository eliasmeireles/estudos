#include <stdio.h>
#include <stdlib.h>

#define TABLE_SIZE 10021

struct aluno {
    int matricula;
    char nome[30];
    float n1, n2, n3;
};


typedef struct aluno Aluno;

Aluno *alunos[TABLE_SIZE];

int totalAlunos = 0;

int chaveHash(int chave) {
    return (chave & 0x7FFFFFFF) % TABLE_SIZE;
}

int posicaoDisponivel(int pos) {
    if (alunos[pos] == NULL) {
        return 1;
    }
    return 0;
}

int tratarColisaoInsere(int pos) {
    if (!posicaoDisponivel(pos)) {
        for (int i = 0; i < TABLE_SIZE; ++i) {
            if (posicaoDisponivel(i)) {
                return i;
            }
        }
    }
    return pos;
}

void encontrarAlunoPorMatricula(int matricula, Aluno *aluno) {
    for (int i = 0; i < TABLE_SIZE; ++i) {
        if(alunos[i] != NULL && alunos[i]->matricula == matricula) {
            *aluno = *(alunos[i]);
            break;
        }
    }
}

int busca(int matricula, Aluno *aluno) {
    int pos = chaveHash(matricula);

    if (alunos[pos] != NULL) {
        if(alunos[pos]->matricula == matricula) {
            *aluno = *(alunos[pos]);
        } else {
            encontrarAlunoPorMatricula(matricula, aluno);
        }
        return 1;
    }
    return 0;
}



int insere(Aluno aluno) {
    if (totalAlunos != TABLE_SIZE) {
        int pos = tratarColisaoInsere(chaveHash(aluno.matricula));
        Aluno *pont = (Aluno *) malloc(sizeof(Aluno));

        if (pont != NULL) {
            *pont = aluno;
            alunos[pos] = pont;
            totalAlunos++;
            printf("%d\n", pos);
            return 1;
        }
    }
    return 0;
}


int main() {
    Aluno alunosLocal[4] = {
            {12353, "Elias",    9.5, 7.0, 8.5},
            {7853,  "Jonathan", 6.5, 3.0, 8.5},
            {3453,  "Renan",    6.5, 3.0, 8.5},
            {5253,  "Kaique",   6.5, 3.0, 8.5}
    };

    for (int i = 0; i < TABLE_SIZE; ++i) {
        alunos[i] = NULL;
    }

    for (int i = 0; i < 4; ++i) {
        insere(alunosLocal[i]);
    }

    Aluno aluno;

    if (busca(7853, &aluno)) {
        printf("Aluno: %s, Matricula: %d \n", aluno.nome, aluno.matricula);
    } else {
        printf("Aluno não encontrado");
    }

    return 0;
}
