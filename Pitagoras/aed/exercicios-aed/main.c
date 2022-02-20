//#include <stdio.h>
//#include <stdlib.h>
//#include <time.h>
//
//void merge(int array[], int leftIndex, int arrayCenterIndex, int rightIndex) {
//    int i, j, k;
//    int sizeOfLeftArray = arrayCenterIndex - leftIndex + 1;
//    int sizeOfRightArray = rightIndex - arrayCenterIndex;
//    int tempLeftArray[sizeOfLeftArray], tempRightArray[sizeOfRightArray];
//    for (i = 0; i < sizeOfLeftArray; i++) {
//        tempLeftArray[i] = array[leftIndex + i];
//    }
//    for (j = 0; j < sizeOfRightArray; j++) {
//        tempRightArray[j] = array[arrayCenterIndex + 1 + j];
//    }
//    i = 0;
//    j = 0;
//    k = leftIndex;
//    while (i < sizeOfLeftArray && j < sizeOfRightArray) {
//        if (tempLeftArray[i] <= tempRightArray[j]) {
//            array[k] = tempLeftArray[i];
//            i++;
//        } else {
//            array[k] = tempRightArray[j];
//            j++;
//        }
//        k++;
//    }
//    while (i < sizeOfLeftArray) {
//        array[k] = tempLeftArray[i];
//        i++;
//        k++;
//    }
//    while (j < sizeOfRightArray) {
//        array[k] = tempRightArray[j];
//        j++;
//        k++;
//    }
//}
//
//void mergeSort(int array[], int leftIndex, int rightIndex) {
//    if (leftIndex < rightIndex) {
//        int arrayCenterIndex = leftIndex + (rightIndex - leftIndex) / 2;
//        mergeSort(array, leftIndex, arrayCenterIndex);
//        mergeSort(array, arrayCenterIndex + 1, rightIndex);
//        merge(array, leftIndex, arrayCenterIndex, rightIndex);
//    }
//}
//
//
//void swap(int *xp, int *yp) {
//    int temp = *xp;
//    *xp = *yp;
//    *yp = temp;
//}
//
//void selectionSort(int array[], int arraySize) {
//    int min_idx;
//
//    for (int i = 0; i < arraySize - 1; i++) {
//        min_idx = i;
//        for (int j = i + 1; j < arraySize; j++) {
//            if (array[j] < array[min_idx]) {
//                min_idx = j;
//            }
//        }
//        swap(&array[min_idx], &array[i]);
//    }
//}
//
//void printArray(int array[], int size) {
//    for (int i = 0; i < size; i++) {
//        printf("%d ", array[i]);
//    }
//    printf("\n");
//}
//
//void print(int size) {
//    double time_spent = 0.0;
//    clock_t begin = clock();
//    int merge[size];
//    int selection[size];
//
//    for (int f = 0; f < size; f++) {
//        merge[f] = (rand() % (size * 10)) + 1;
//        selection[f] = (rand() % (size * 10)) + 1;
//    }
//
//    double mergeSortTime = 0.0;
//    clock_t mergeBeginTime = clock ();
//
//    mergeSort(merge, 0, size);
//
//    clock_t mergeEnd = clock ();
//    mergeSortTime += (double) (mergeEnd - mergeBeginTime) / CLOCKS_PER_SEC;
//    printf("Utilizando MergeSort com %d elementos\n", size);
//    printArray(merge, size);
//    printf("O tempo gasto para o MergeSort foi de %f segundos\n", mergeSortTime);
//
//    double selectionSortTime = 0.0;
//    clock_t selectionBeginTime = clock ();
//
//    selectionSort(selection, size);
//
//    clock_t selectionEnd = clock ();
//    selectionSortTime += (double) (selectionEnd - selectionBeginTime) / CLOCKS_PER_SEC;
//    printf("\nUtilizando SelectionSort com %d elementos\n", size);
//    printArray(selection, size);
//    printf("O tempo gasto para o SelectionSort foi de %f segundos\n\n", selectionSortTime);
//}
//
//int main() {
//    print(10);
//
//    print(10000);
//    return 0;
//}