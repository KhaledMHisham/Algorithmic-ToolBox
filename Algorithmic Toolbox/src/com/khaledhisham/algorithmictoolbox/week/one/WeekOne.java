package com.khaledhisham.algorithmictoolbox.week.one;

public class WeekOne {
    // Max Pairwise Product Problem.
    private static long maxPairWiseProduct(long[] arr){
        long result = 0;
        for (int i = 0; i < arr.length ; ++i){
            for (int j = i+1; j < arr.length; ++j){
                if(arr[i] * arr[j] > result){
                    result = arr[i] * arr[j];
                }
            }
        }
        return result;
    }
    private static long maxPairWiseProductFast(long[] arr){
        long result = 0;
        int maxIndex1 = -1 ;


        for(int i = 0 ; i < arr.length; ++i){
            if(maxIndex1 == -1 || (arr[i] > arr[maxIndex1])){
                maxIndex1 = i;
            }
        }
        int maxIndex2 = -1;
        for (int j = 0 ; j < arr.length; ++j){
            if (maxIndex1 != j && (maxIndex2 == -1 || arr[j] > arr[maxIndex2] )){
                maxIndex2 = j;
            }
        }
        result = arr[maxIndex1] * arr[maxIndex2];
        return result;
    }

    // Util Functions HERE.
    private static void stressTest(){
        while(true){
            int n = (int) (Math.random() * 12) + 2;
            System.out.println(n);

            long[] arr = new long[n];
            for(int i = 0 ; i < n  ; ++i){
                arr[i] = (int) (Math.random() * 100000);
            }
            for (int i = 0; i < n; ++i){
                System.out.print(arr[i]+" ");
            }
            System.out.print("\n");
            long res1 = maxPairWiseProduct(arr);
            long res2 = maxPairWiseProductFast(arr);
            if (res1 != res2){
                System.out.print("Wrong answer: " + res1 + " " + res2);
                break;
            }
            else{
    System.out.print("OK!\n");
            }
        }
    }
}
