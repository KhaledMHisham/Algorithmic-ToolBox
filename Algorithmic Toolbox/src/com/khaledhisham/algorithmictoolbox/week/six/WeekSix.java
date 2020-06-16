package com.khaledhisham.algorithmictoolbox.week.six;

public class WeekSix {

    public static int maximumAmountOfGold(int weight, int[] weights){
        int[][] m = new int[weights.length + 1][weight + 1];
        for(int i = 1 ; i < m.length ; ++i){
            for(int j = 1 ; j < m[0].length ; ++j){
                if(j - weights[i-1] >= 0 && m[i - 1][j] < m[i - 1][j - weights[i - 1]] + weights[i - 1]){
                    m[i][j] = m[i - 1][j - weights[i - 1]] + weights[i - 1];
                }
                else{
                    m[i][j] = m[i - 1][j];
                }
            }
        }
        return m[weights.length][weight];
    }
    public static void knapsackWithRepetition(int weight, int[] weights, int[] revenues){
        int[] m = new int[weight + 1];
        for(int i = 0; i < weight + 1 ; ++i){
            for(int j = 0; j < weights.length ; ++j){
                if(i - weights[j] >= 0 && m[i] < m[i - weights[j]] + revenues[j]){
                    m[i] = m[i - weights[j]] + revenues[j];
                }
            }
        }
        printArray(m, "Memo");
    }
    public static void knapsackWithoutRepetition(int weight, int[] weights, int[] revenues){
        int[][] m = new int[weights.length + 1][weight + 1];
        for(int i = 1 ; i < m.length ; ++i){
            for(int j = 1 ; j < m[0].length ; ++j){
                if(j - weights[i-1] >= 0 && m[i - 1][j] < m[i - 1][j - weights[i - 1]] + revenues[i - 1]){
                    m[i][j] = m[i - 1][j - weights[i - 1]] + revenues[i - 1];
                }
                else{
                    m[i][j] = m[i - 1][j];
                }
            }
        }
        print2DArray(m);
    }
    private static void printArray( int[] arr, String arrayName){
        System.out.println( arrayName + " array has been set : " );
        for(int i = 0 ; i < arr.length ; ++i){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    private static void print2DArray(int[][] A){
        for(int i = 0 ; i < A.length ; ++i){
            for(int j = 0; j < A[0].length ; ++j){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
