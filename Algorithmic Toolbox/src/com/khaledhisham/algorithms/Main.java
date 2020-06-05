package com.khaledhisham.algorithms;
public class Main {
    public static void main(String[] args) {
        stressTest();
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        System.out.println(cutRod(prices, prices.length));
    }

    // Cut Rod Problem.
    private static int cutRod(int prices[], int n) {
        if (n <= 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            q = Math.max(q, prices[i] + cutRod(prices, n - i - 1));
        }
        return q;
    }
    private static int cutRodTopDown(int prices[], int n) {
        int revenues[] = new int[prices.length + 1];
        for (int i = 0; i < revenues.length; ++i) {
            revenues[i] = Integer.MIN_VALUE;
        }
        return memoizedCutRodTopDown(prices, n, revenues);
    }
    private static int memoizedCutRodTopDown(int[] prices, int n, int[] revenues) {
        int q = Integer.MIN_VALUE;
        if (revenues[n] >= 0) {
            return revenues[n];
        }
        if (n == 0) {
            q = 0;
            revenues[n] = q;
        } else {
            for (int i = 0; i < n; ++i) {
                q = Math.max(q, prices[i] + memoizedCutRodTopDown(prices, n - i - 1, revenues));
                revenues[n] = q;
            }
        }
        return revenues[n];
    }

    // Util Functions HERE.
    private static void stressTest() {
        while (true) {
            int n = (int) (Math.random() * 12) + 2;
            System.out.println(n);

            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = (int) (Math.random() * 100000);
            }
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }
            System.out.print("\n");
            long res1 = cutRod(arr, arr.length);
            long res2 = cutRodTopDown(arr, arr.length);
            if (res1 != res2) {
                System.out.print("Wrong answer: " + res1 + " " + res2);
                break;
            } else {
                System.out.print("OK!\n");
            }
        }
    }

    private static void printArray(int[] arr, String arrayName) {
        System.out.println(arrayName + " array has been set : ");
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
    }
}