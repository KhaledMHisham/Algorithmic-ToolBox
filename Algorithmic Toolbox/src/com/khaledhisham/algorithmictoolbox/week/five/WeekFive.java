package com.khaledhisham.algorithmictoolbox.week.five;

public class WeekFive {

    // Money Change Problem.
    private static int moneyChange(int money) {
        int[] m = new int[money + 1];
        int[] change = {0, 1, 3, 4};
        for(int i = 0; i < m.length ; ++i){
            m[i] = Integer.MAX_VALUE;
            for(int j = 0; j < change.length; ++j){
                if(i == change[j]){
                    m[i] = change[j];
                }
            }
        }
        for(int i = 1 ; i  < m.length ; ++i){
            m[i] = Integer.MAX_VALUE;
            for(int j = 0 ; j < change.length ; ++j){
                m[i] = Math.min(m[i],getMin(lookupMemoization(m, i - change[j])));
            }
            m[i]++;
        }
        return m[money];
    }
    private static int lookupMemoization(int[] m, int index){
        if(index >= 0){
            return m[index];
        }
        return Integer.MAX_VALUE;
    }

    //Primitive Calculator
    public static int[][] primitiveCalculator(int n){
        int[] m = new int[n+1];
        int [] solution = new int[n+1];
        m[0] = 0;
        m[1] = 0;
        solution[0] = 0;
        solution[1] = 0;
        for(int i = 2 ; i < m.length ; ++i){
            m[i] = Integer.MAX_VALUE;
            solution[i]= -1;
        }
        for(int i = 2 ; i < m.length ; ++i){
            if(i%3 == 0 && i%2 == 0){
                int[] min = getMinModified(m[i-1], m[i/3], m[i/2]);
                m[i] = min[0] ;
                solution[i] = fillSolution(min[1], i);
            }
            else if(i % 3 == 0){
                m[i] = m[i-1] < m[i/3] ? m[i-1] : m[i/3];
                solution[i] = m[i-1] < m[i/3] ? i-1 : i/3;
            }
            else if(i % 2 == 0){
                m[i] = m[i-1] < m[i/2] ? m[i-1] : m[i/2];
                solution[i] = m[i-1] < m[i/2] ? i-1 : i/2;
            }
            else{
                m[i] = m[i - 1];
                solution[i] = i - 1;
            }
            m[i]++;
        }
        int[][] result = {m, solution};
        return result;
    }

    // Rod Cutting Problem.
    private static int cutRod(int prices[], int n){
        if(n <= 0){
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; ++i){
            q = Math.max(q, prices[i] + cutRod(prices, n - i - 1));
        }
        return q;
    }
    private static int cutRodTopDown(int prices[], int n){
        int revenues[] = new int[prices.length + 1];
        for(int i = 0 ; i < revenues.length ; ++i){
            revenues[i] = Integer.MIN_VALUE;
        }
        return memoizedCutRodTopDown(prices, n, revenues);
    }

    // Memoized Cut Rod Top Down / Bottom Up O(n^2).
    private static int memoizedCutRodTopDown(int[] prices, int n, int[] revenues) {
        int q = Integer.MIN_VALUE;
        if(revenues[n] >= 0){
            return revenues[n];
        }
        if(n == 0){
            q = 0;
            revenues[n] = q;
        }
        else{
            for(int i =0; i < n ; ++i){
                q = Math.max(q, prices[i] + memoizedCutRodTopDown(prices, n - i - 1, revenues));
                revenues[n] = q;
            }
        }
        return revenues[n];
    }
    private static int bottomUpCutRod(int[] prices, int n){
        int[] revenues = new int[n+1];
        revenues[0] = 0;
        for(int i = 1 ; i < n + 1 ; ++i){
            int q = Integer.MIN_VALUE;
            for(int j = 0 ; j < i ; ++j ){
                q = Math.max(q, prices[j] + revenues[i - j - 1]);
            }
            revenues[i] = q;
        }
        return revenues[n];
    }

    // Matrix Chain Multiplication.
    private static int[][] matrixMultiply(int[][] A, int[][] B) throws IncompatibleDimensions{
        if(A[0].length != B.length){
            throw new IncompatibleDimensions("A[Columns] != B[Rows]");
        }
        int[][] C = new int[A.length][B[0].length];
        for(int i = 0 ; i < A.length ; ++i){
            for(int k = 0 ; k < B[0].length ; ++k){
                C[i][k] = 0;
                for(int j = 0; j < B.length ; ++j){
                    C[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return C;
    }
    private static int matrixChainMultiplication(int[][][] A){
        int[][] m = new int[A.length][A.length];
        return memoizedMatrixChainMultiplication(A,m, 0, A.length - 1);
    }
    private static int memoizedMatrixChainMultiplication(int[][][] A, int[][] m, int l, int h) {
        if(h - l < 2){
            int i = A[l].length;
            int j = A[l][0].length;
            int k = A[h][0].length;
            m[l][h] = i * j * k;
            return m[l][h];
        }
        if(m[l][h] == 0){
            m[l][h] = Math.min(memoizedMatrixChainMultiplication(A, m, l, h-1) + A[l].length * A[h-1][0].length * A[h][0].length,
                    memoizedMatrixChainMultiplication(A, m, l + 1, h) + A[l].length * A[l+1].length * A[h][0].length);
        }
        return m[l][h];
    }

    // Edit Distance.
    public static int EditDistance(String s, String t) {
        int[][] m = new int[s.length() + 1][t.length() + 1];
        for(int i = 0 ; i < m.length ; ++i)
            m[i][0] = i;
        for(int i = 0 ; i < m[0].length ; ++i)
            m[0][i] = i;
        for(int i = 1 ; i < m.length ; ++i){
            for(int j = 1 ; j < m[0] .length ; ++j){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    m[i][j] = getMin(m[i-1][j] + 1, m[i-1][j-1], m[i][j-1] + 1);
                }
                else{
                    m[i][j] = getMin(m[i-1][j] + 1, m[i-1][j-1] + 1, m[i][j-1] + 1);
                }
            }
        }
        return m[s.length()][t.length()];
    }

    // Util Functions HERE.
    private static void stressTest(){
        while(true){
            int n = (int) (Math.random() * 12) + 2;
            System.out.println(n);

            int[] arr = new int[n];
            for(int i = 0 ; i < n  ; ++i){
                arr[i] = (int) (Math.random() * 100000);
            }
            for (int i = 0; i < n; ++i){
                System.out.print(arr[i]+" ");
            }
            System.out.print("\n");
            long res1 = bottomUpCutRod(arr, arr.length);
            long res2 = cutRodTopDown(arr, arr.length);
            if (res1 != res2){
                System.out.print("Wrong answer: " + res1 + " " + res2);
                break;
            }
            else{
                System.out.print("OK!\n");
            }
        }
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
    private static int getMin(int ...x){
        int result = x[0];
        for(int i = 0; i < x.length ; ++i){
            if(result > x[i]){
                result = x[i];
            }
        }
        return result;
    }
    private static int[] getMinModified(int ...x){
        int[] result = new int[2];
        result[0]= Integer.MAX_VALUE;
        for(int i = 0; i < x.length ; ++i){
            if(result[0] > x[i]){
                result[0] = x[i];
                result[1] = i;
            }
        }
        return result;
    }
    private static int fillSolution(int value , int index){
        switch (value){
            case 0:
                return index - 1;
            case 1 :
                return index / 3;
            case 2 :
                return index / 2;
        }
        return 0;
    }
    private static void printSolution(int[] solution, int h){
        int i = h;
        if(solution[i] == 0){
            return;
        }
        printSolution(solution, solution[i]);
        System.out.print(solution[i] + " ");
    }

}
