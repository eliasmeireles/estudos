#include <stdio.h>
void merge(int array[], int leftIndex, int arrayCenterIndex, int rightIndex)
{
    int i, j, k;
    int sizeOfLeftArray = arrayCenterIndex - leftIndex + 1;
    int sizeOfRightArray = rightIndex - arrayCenterIndex;
    int tempLeftArray[sizeOfLeftArray], tempRightArray[sizeOfRightArray];
    for (i = 0; i < sizeOfLeftArray; i++) {
        tempLeftArray[i] = array[leftIndex + i];
    }
    for (j = 0; j < sizeOfRightArray; j++) {
        tempRightArray[j] = array[arrayCenterIndex + 1 + j];
    }
    i = 0;
    j = 0;
    k = leftIndex;
    while (i < sizeOfLeftArray && j < sizeOfRightArray) {
        if (tempLeftArray[i] <= tempRightArray[j]) {
            array[k] = tempLeftArray[i];
            i++;
        } else {
            array[k] = tempRightArray[j];
            j++;
        }
        k++;
    }
    while (i < sizeOfLeftArray) {
        array[k] = tempLeftArray[i];
        i++;
        k++;
    }
    while (j < sizeOfRightArray) {
        array[k] = tempRightArray[j];
        j++;
        k++;
    }
}

void mergeSort(int array[], int leftIndex, int rightIndex) {
    if (leftIndex < rightIndex) {
        int arrayCenterIndex = leftIndex + (rightIndex - leftIndex) / 2;
        mergeSort(array, leftIndex, arrayCenterIndex);
        mergeSort(array, arrayCenterIndex + 1, rightIndex);
        merge(array, leftIndex, arrayCenterIndex, rightIndex);
    }
}
void printArray(int array[], int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");
}
int main() {
    int arr[10] = {90, 2, 5, 6, 100, 8, 9, 10, 8, -1};
    printf("Vetor antes da ordanação \n");
    printArray(arr, 10);
    mergeSort(arr, 0, 10);
    printf("\nVetor pós ordenação \n");
    printArray(arr, 10);
    return 0;
}