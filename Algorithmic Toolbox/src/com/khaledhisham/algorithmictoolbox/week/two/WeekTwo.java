package com.khaledhisham.algorithmictoolbox.week.two;

public class WeekTwo {

    // Fibonacci Numbers.
    private static long fibRecursSlow(int n){
        if (n <= 1){ return n;}
        return fibRecursSlow(n-1 ) + fibRecursSlow(n-2);
    }
    private static long fibFast(int n){
        if(n < 2){
            return n;
        }
        long[] arr = new long[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2 ; i < arr.length; ++i){
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }
    private static int getFibonacciLastDigit(int n){
        if(n < 2){
            return n;
        }
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2; i < arr.length; ++i){
            arr[i] = (arr[i-1] + arr[i-2]) % 10;
        }
        return  (int)arr[n];
    }
    private static long lastDigitOfTheSumOfFibonacciNumbers(long n){
        if(n < 2){
            return n;
        }
        int[] arr = new int[15000001];
        arr[0] = 0;
        arr[1] = 1;
        int count = 0;
        int periodWidth = 0;
        int periodSum = 0;
        for(int i = 2 ; i < arr.length; ++i){
            arr[i] = (arr[i-1] + arr[i-2]) % 10;
            periodSum += arr[i-2];
            if(arr[i-2] == 0 && arr[i-1] == 1){
                count++;
                if(count == 2){
                    periodWidth = i-2;
                    break;
                }
            }
        }
        long numberOfPeriods = n / periodWidth;
        long partOfPeriod = n % periodWidth + 1;
        int partSum = 0;
        for(int i = 0; i < partOfPeriod; ++i){
            partSum += arr[i];
        }
        //System.out.println("Period Sum is :" + periodSum);
        //System.out.println("Part Sum is :" + partSum);
        //System.out.println("Number of Periods is :" + numberOfPeriods);
        //System.out.println("Extra Part Of Period is :" + partOfPeriod);
        //System.out.println("Width Of Period is :" + periodWidth);

        long result = periodSum * numberOfPeriods + partSum;
        return result % 10;
    }
    private static long lastDigitOfTheSumOfSquaresOfFibonacciNumbers(long n){
        if(n < 2){
            return n;
        }
        int[] arr = new int[15000001];
        arr[0] = 0;
        arr[1] = 1;
        int count = 0;
        int periodWidth = 0;
        int periodSquareSum = 0;
        for(int i = 2 ; i < arr.length; ++i){
            arr[i] = (arr[i-1] + arr[i-2]) % 10;
            periodSquareSum += Math.pow(arr[i-2], 2);
            if(arr[i-2] == 0 && arr[i-1] == 1){
                count++;
                if(count == 2){
                    periodWidth = i-2;
                    break;
                }
            }
        }
        long numberOfPeriods = n / periodWidth;
        long partOfPeriod = n % periodWidth + 1;
        int partSquareSum = 0;
        for(int i = 0; i < partOfPeriod; ++i){
            partSquareSum += Math.pow(arr[i],2);
        }
        //System.out.println("Period Sum is :" + periodSum);
        //System.out.println("Part Sum is :" + partSum);
        //System.out.println("Number of Periods is :" + numberOfPeriods);
        //System.out.println("Extra Part Of Period is :" + partOfPeriod);
        //System.out.println("Width Of Period is :" + periodWidth);

        long result = periodSquareSum * numberOfPeriods + partSquareSum;
        return result % 10;
    }
    private static long lastDigitOfTheSumOfFibonacciNumbersRanged(long a, long b){
        long startPoint = 0;
        if (a == 0) {
            startPoint = lastDigitOfTheSumOfFibonacciNumbers(a);
        }
        else {
            startPoint = lastDigitOfTheSumOfFibonacciNumbers(a - 1);
        }
        long endPoint = lastDigitOfTheSumOfFibonacciNumbers(b);
        long result = endPoint - startPoint;
        if(result < 0){
            result += 10;
        }
        return result;
    }
    private static long getFibonacciModulus(long n, long m){
        if(n < 2){
            return n;
        }
        int[] arr = new int[15000001];
        arr[0] = 0;
        arr[1] = 1;
        int count = 0;
        int periodWidth = 0;
        for(int i = 2 ; i < arr.length; ++i){
            arr[i] = (arr[i-1] + arr[i-2]) % (int)m;
            if(arr[i-2] == 0 && arr[i-1] == 1){
                count++;
                if(count == 2){
                    periodWidth = i-2;
                    break;
                }
            }
        }
        int resultIndex = (int) (n % periodWidth);
        int result = arr[resultIndex];
        return result;
    }

    // Greatest Common Divisor Problem
    private static long gcdNaive(long a, long b){
        long best = 0;
        for(long i = 1 ; i < a+b; ++i){
            if(a%i == 0 && b%i == 0){
                best = i;
            }
        }
        return best;
    }
    private static long gcdRecurs(long a, long b){
        if(b == 0){
            return a;
        }
        long remainder = a % b;
        return gcdRecurs(b,remainder);
    }

    // Least Common Multiplier.
    private static long lcmNaive(int a, int b) {
        for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
    }
    private static long lcmRecurs(long a, long b){
        long result = gcdRecurs(a,b);
        long lcmResult = a * b / result;
        return lcmResult;
    }

}
