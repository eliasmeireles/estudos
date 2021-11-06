#include <stdio.h>

const int MAX = 100;

void promoveElemento(int fp[], int filho) {
    int pai = (filho - 1) / 2;

    while (filho > 0 && fp[pai] < fp[filho]) {
        int temp = fp[filho];
        fp[filho] = fp[pai];
        fp[pai] = temp;

        filho = pai;
        pai = (filho - 1) / 2;
    }
}

int insert(int fp[], int *qtd, int prior) {
    if (*qtd == MAX) {
        return 0;
    }
    fp[*qtd] = prior;
    promoveElemento(fp, *qtd);
    (*qtd)++;
    return 1;
}

void rebaixaElemento(int fp[], int pai, int qtd) {

}

int remover(int fp[], int *qtd) {
    if(*qtd > 0) {
        (*qtd)--;
        fp[0] = fp[*qtd];
        return 1;
    }
    return -1;
}

void findParents(int fp[], int *qtd) {
    int count = 0;
    int direita = (2 * count) + 2;
    int esquerda = (2 * count) + 1;

    while (esquerda < *qtd) {
        printf("Elemento(%d) -> Finlho da direita:(%d), Filho da esquerda(%d) \n", fp[count], fp[direita], fp[esquerda]);
        count++;
        direita = (2 * count) + 1;
        esquerda = (2 * count) + 2;
    }
}

//int main() {
//    int size = 6;
//    int fp[MAX], qtd = 0;
//
//    int itens[] = {1, 2, 5, 8, 6, 4};
//    printf("Intens a serem incluidos: \n");
//    for (int i = 0; i < size; ++i) {
//        printf("%d ", itens[i]);
//    }
//    printf("\n");
//
//    for (int i = 0; i < size; ++i) {
//        insert(fp, &qtd, itens[i]);
//    }
//    printf("Fila de priorida - carga inicial: \n");
//    insert(fp, &qtd, 9);
//    insert(fp, &qtd, 7);
//
//    for (int i = 0; i < qtd; ++i) {
//        printf("%d ", fp[i]);
//    }
//
//    printf("\n");
//
//    remover(fp, &qtd);
//
//    printf("Fila após a remoção: \n");
//    for (int i = 0; i < qtd; ++i) {
//        printf("%d ", fp[i]);
//    }
//    return 0;
//}