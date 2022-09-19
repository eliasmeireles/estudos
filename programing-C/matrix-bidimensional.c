#include <stdio.h>

int getValue(int x, int y) {
    if(x == y) {
        return x;
    } 
    if(x > y) {
        return y;
    }
    return x;
}

int main()
{
    int matrixDim = 40;
    int matrixHalf = matrixDim / 2;
    
    int matrix[matrixDim][matrixDim];
    
    for (int i = 0; i < matrixDim; i++) {
        for (int j = 0; j < matrixDim; j++) {
            matrix[i][j] = 0;
        }
    }

    for (int i = 1; i <= matrixHalf; i++) {
        for (int j = 1; j <= matrixHalf; j++) {
            matrix[i - 1][j - 1] = getValue(i, j);
            matrix[i - 1][matrixDim -j] = getValue(i, j);
        }
    }
    
    
    for (int i = matrixDim; i > matrixHalf; i--) {
        for (int j = matrixDim; j > 0; j--) {
            matrix[i - 1][j - 1] = matrix[matrixDim - i][matrixDim - j];
        }
    }
    
    
   for (int i = 0; i < matrixDim; i++) {
        for (int j = 0; j < matrixDim; j++) {
            int intensit = matrix[i][j] + 40;
            if (intensit > 41) {
                intensit = 41;
            }
            printf("\33[%dm%s ", intensit, " ");
            // printf("\33[41m%d ", matrix[i][j]);
            // printf("\033[31;%d;%dm%d ", intensit, intensit - 1, matrix[i][j]);
            // printf("\x1B[%dm%d ", matrix[i][j] + 30, matrix[i][j]);
            // printf("\33[%dm%s ", matrix[i][j] + 100, " ");
        }
        printf("\33[0m\n");
    } 
    
     printf("\n\n");
    return 0;
}
