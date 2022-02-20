#include <malloc.h>

struct NO {
    int info;
    struct NO *esq;
    struct NO *dir;
};

typedef struct NO *ArvoreBinaria;

ArvoreBinaria *criarArvore() {
    ArvoreBinaria *raiz = (ArvoreBinaria *) malloc(sizeof(ArvoreBinaria));
    if (raiz != NULL)*raiz = NULL;
    return raiz;
}

void liberaNo(struct NO *no) {
    if (no == NULL) return;
    liberaNo(no->esq);
    liberaNo(no->dir);
    free(no);
}

void liberaArvoreBinaria(ArvoreBinaria *raiz) {
    if (raiz == NULL)return;
    liberaNo(*raiz);
    free(raiz);
}

int insereArvoreBinaria(ArvoreBinaria *raiz, int valor) {
    if (raiz == NULL) return 0;
    struct NO *novo;
    novo = (struct NO *) malloc(sizeof(struct NO));
    if (novo == NULL) return 0;
    novo->info = valor;
    novo->dir = NULL;
    novo->esq = NULL;
    if (*raiz == NULL) {
        *raiz = novo;
        return 1;
    }
    struct NO *atual = *raiz;
    struct NO *ant = NULL;
    while (atual != NULL) {
        ant = atual;
        if (valor == atual->info) {
            free(novo);
            return 0;
        }
        if (valor > atual->info) {
            atual = atual->dir;
        } else {
            atual = atual->esq;
        }
    }
    if (valor > ant->info) {
        ant->dir = novo;
    } else {
        ant->esq = novo;
    }
    return 1;
}

void preOrdemArvoreBinaria(ArvoreBinaria *raiz) {
    if (*raiz != NULL) {
        printf("%d ", (*raiz)->info);
        preOrdemArvoreBinaria(&((*raiz)->esq));
        preOrdemArvoreBinaria(&((*raiz)->dir));
    }
}

void emOrdemArvoreBinaria(ArvoreBinaria *raiz) {
    if (*raiz != NULL) {
        emOrdemArvoreBinaria(&((*raiz)->esq));
        printf("%d ", (*raiz)->info);
        emOrdemArvoreBinaria(&((*raiz)->dir));
    }
}

void posOrdemArvoreBinaria(ArvoreBinaria *raiz) {
    if (*raiz != NULL) {
        posOrdemArvoreBinaria(&((*raiz)->esq));
        posOrdemArvoreBinaria(&((*raiz)->dir));
        printf("%d ", (*raiz)->info);
    }
}

ArvoreBinaria *consultaArvoreBinaria(ArvoreBinaria *raiz, int valor) {
    if (*raiz != NULL) {
        ArvoreBinaria *atual = raiz;
        while (*atual != NULL) {
            if (valor == (*atual)->info) {
                return atual;
            }
            if (valor > (*atual)->info) {
                atual = &((*atual)->dir);
            } else {
                atual = &((*atual)->esq);
            }
        }
    }
    return NULL;
}

int ehNoFolha(ArvoreBinaria *raiz) {
    if ((*raiz)->dir == NULL && (*raiz)->esq == NULL) {
        return 1;
    }
    return 0;
}

ArvoreBinaria *encntarOMaior(ArvoreBinaria *raiz) {
    struct NO *no = *raiz;
    if (no != NULL && no->dir != NULL) {
        return encntarOMaior(&((*raiz)->dir));
    }
    return raiz;
}

int removeArvore(ArvoreBinaria *raiz, int valor) {
    ArvoreBinaria *noAhRemover = consultaArvoreBinaria(raiz, valor);
    if (noAhRemover != NULL) {
        if (ehNoFolha(noAhRemover)) {
            (*noAhRemover) = NULL;
        } else if ((*noAhRemover)->esq != NULL) {
            ArvoreBinaria *maior = encntarOMaior(&((*noAhRemover)->esq));
            (*noAhRemover)->info = (*maior)->info;
            (*maior) = NULL;
        } else {
            (*noAhRemover) = (*noAhRemover)->dir;
        }
        return 1;
    }
    return 0;
}

#define TOTAL   9

int main() {
    ArvoreBinaria *arvoreBinaria = criarArvore();

    int dados[TOTAL] = {50, 100, 40, 45, 30, 33, 31, 35, 101};

    for (int i = 0; i < TOTAL; ++i) {
        insereArvoreBinaria(arvoreBinaria, dados[i]);
    }

    if (removeArvore(arvoreBinaria, 100)) {
        printf("\nElemento removido com sucesso.");
    } else {
        printf("\nElemento não pode ser removido.");
    }

    printf("\nPre ordem: ");
    preOrdemArvoreBinaria(arvoreBinaria);

    printf("\nEm ordem: ");
    emOrdemArvoreBinaria(arvoreBinaria);

    printf("\nPós ordem: ");
    posOrdemArvoreBinaria(arvoreBinaria);

    liberaArvoreBinaria(arvoreBinaria);
    return 0;
}
