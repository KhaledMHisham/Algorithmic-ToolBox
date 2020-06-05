package com.khaledhisham.algorithmictoolbox.week.three;

public class WeekThree {

    // Money Change Problem.
    private static int getChange(int m) {
        int[] coinValues = {10, 5, 1};
        int result = 0;
        int coinCount = 0;
        int i = 0;
        while (m > 0){
            while(i < coinValues.length && m >= coinValues[i]){
                m-=coinValues[i];
                result += coinValues[i];
                coinCount++;
            }
            i++;
        }
        return coinCount;
    }

    // Knapsack Problem.
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        double[] valuesPerWeights = new double[values.length];
        int a = 0;
        for(int i = 0 ; i < valuesPerWeights.length; ++i){
            if(weights[i] == 0){
                continue;
            }
            valuesPerWeights[i] = (double)values[i] / (double)weights[i];
        }
        for(int i = 0; i < valuesPerWeights.length; ++i){
            for(int j = i+1; j < valuesPerWeights.length; ++j){
                if(valuesPerWeights[i] < valuesPerWeights[j]){
                    double temp;
                    temp = valuesPerWeights[i];
                    valuesPerWeights[i] = valuesPerWeights[j];
                    valuesPerWeights[j] = temp;
                    // Swapping Values Accordingly.
                    temp = values[i];
                    values[i] = values[j];
                    values[j] = (int) temp;
                    // Swapping Weights Accordingly
                    temp = weights[i];
                    weights[i] = weights[j];
                    weights[j] = (int) temp;
                }
            }
        }
        for(int i = 0; i < valuesPerWeights.length ; ++i){
            if(capacity == 0){
                return value;
            }
            if(weights[i] <= capacity){
                capacity -= weights[i];
                value += values[i];
            }
            else if(weights[i] > capacity){
                value += capacity * valuesPerWeights[i];
                capacity = 0;
            }
        }
        return value;
    }

    // Min Car Fuel Refill Problem.
    private static int computeMinRefills(int dist, int tank, int[] stops) {
        int numOfRefills = 0, currentTank;
        int[] totalStops = new int[stops.length+2];
        totalStops[0] = 0;
        currentTank = tank;
        totalStops[totalStops.length-1] = dist;
        for(int i = 0 ; i < stops.length; ++i){
            totalStops[i+1] = stops[i];
        }
        if(dist - stops[stops.length-1] > tank || stops[0] > tank){
            return -1;
        }
        if(dist <= tank){
            return 0;
        }
        for(int i = 0 ; i < totalStops.length - 1 ; ++i){
            if(totalStops[i+1] - totalStops[i] > tank){
                return -1;
            }
            else if(totalStops[i+1] - totalStops[i] <= currentTank){
                currentTank -= totalStops[i+1] - totalStops[i];
            }
            else{
                numOfRefills++;
                currentTank = tank;
                currentTank -= totalStops[i+1] - totalStops[i];
            }
        }
        return numOfRefills;
    }

    // Max Dot Product Problem.
    private static long maxDotProduct(long[] a, long[] b) {
        a = sort(a);
        b = sort(b);
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    // Util Function Here
    private static long[] sort(long[] arr){
        for(int i = 0 ; i < arr.length ; ++i){
            for(int j = i+1; j < arr.length; ++j){
                if(arr[i] > arr[j]){
                    long temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

}
