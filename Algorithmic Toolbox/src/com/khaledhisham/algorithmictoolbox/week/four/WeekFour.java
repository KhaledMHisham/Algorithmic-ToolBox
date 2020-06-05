package com.khaledhisham.algorithmictoolbox.week.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WeekFour {
    // Brute Implementation Sort Algorithms Here.
    private static int[] bubbleSort(int[] arr){
        for(int i = 0 ; i < arr.length ; ++i){
            for(int j = i+1 ; j < arr.length ; ++j){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
    private static int[] selectionSort(int[] arr){
        int minVal = 2147483647;
        int minIndex = -1;
        for(int i = 0 ; i < arr.length ; ++i) {
            for (int j = i; j < arr.length; ++j) {
                if (arr[j] < minVal) {
                    minVal = arr[j];
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
    private static int[] insertionSort(int[] arr){

        return arr;
    }

    // Merge Sort.
    private static int[] mergeSort(int[] arr){
        if(arr.length == 1 ){
            return arr;
        }
        int[] arrA = new int[arr.length / 2];
        System.arraycopy(arr, 0, arrA, 0, arrA.length);
        arrA = mergeSort(arrA);
        int[] arrB = new int[arr.length - arr.length / 2];
        System.arraycopy(arr, arr.length / 2, arrB, 0, arrB.length);
        arrB = mergeSort(arrB);
        int[] arrMerged = merge(arrA, arrB);
        return arrMerged;
    }
    private static int[] merge(int[] arrA, int[] arrB){
        int [] arr = new int[arrA.length + arrB.length];
        int k = 0;
        int i = 0;
        int j = 0;
        while(i < arrA.length && j < arrB.length) {
            if (arrA[i] <= arrB[j]) {
                arr[k] = arrA[i];
                i++;
            }
            else {
                arr[k] = arrB[j];
                j++;
            }
            k++;
        }
        while(i < arrA.length){
            arr[k] = arrA[i];
            i++;
            k++;
        }
        while(j < arrB.length){
            arr[k] = arrB[j];
            j++;
            k++;
        }
        return arr;
    }

    // Randomized Quick Sort.
    private static int partition(int[] a, int l, int r) {
        int pivot = a[l];
        int i = l+1;
        int j = l;
        while(i <= r){
            if(a[i] <= pivot){
                swapItemsInArray(a, i, j+1);
                j++;
            }
            i++;
        }
        swapItemsInArray(a, l, j);
        return j;
    }
    private static void randomizedQuickSort(int[] a, int l, int r){
        if(l >= r){
            return;
        }
        //Randomizing the pivot.
        int randomIndex = l + (int) Math.random() * (r - l + 1);
        swapItemsInArray(a, l, randomIndex);
        int m = partition(a, l, r);
        randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);
    }

    // Three Way Randomized Quick Sort.
    private static int[] threeWayRandomizedQuickSort(int[] a, int l, int r){
        if(l >= r){
            return a;
        }
        int[] m = threeWayPartition(a, l, r);
        int[] arr1 = threeWayRandomizedQuickSort(a, l, m[0] - 1);
        int[] arr2 = threeWayRandomizedQuickSort(a, m[1] + 1, r);
        return a;
    }
    private static int[] threeWayPartition(int[] a, int l, int r) {
        int i = l+1, j = l, k = l, pivot = a[l];
        while(i <= r){
            if(a[i] < pivot){
                swapItemsInArray(a, i, j+1);
                if(j != k) {
                    swapItemsInArray(a, i, k + 1);
                }
                k++;
                j++;
            }
            else if(a[i] == pivot){
                swapItemsInArray(a, i, k+1);
                k++;
            }
            i++;
        }
        swapItemsInArray(a, l, j);
        int[] indices = {j, k};

        return indices;
    }

    // Number Of Inversions Problem.
    private static int getNumberOfInversionsRecurs(int[] arr, int l, int r) {
        int count = 0;
        if(l >= r){
            return count;
        }
        int m = (l + r) / 2;
        count += getNumberOfInversionsRecurs(arr, l, m);
        count += getNumberOfInversionsRecurs(arr, m+1, r);
        count += mergeModified(arr,l,r,m);
        return count;
    }
    private static int mergeModified(int[] arr, int l, int r, int m) {
        int[] arrA = cpSubArrayFromArray(arr, l, m);
        int[] arrB = cpSubArrayFromArray(arr, m + 1, r);

        int i = 0, j = 0, k = l, count = 0;

        while (i < arrA.length && j < arrB.length) {
            if (arrA[i] <= arrB[j])
                arr[k++] = arrA[i++];
            else {
                arr[k++] = arrB[j++];
                count += arrA.length - i;
            }
        }
        while (i < arrA.length) {
            arr[k++] = arrA[i++];
        }
        while (j < arrB.length) {
            arr[k++] = arrB[j++];
        }
        return count;
    }
    private static int getNumberOfInversions(int[] a) {
        int numberOfInversions = 0;
        for(int i = 0 ; i < a.length ; ++i){
            for(int j = 0; j < a.length ; ++j){
                if(i < j && a[i] > a[j]){
                    numberOfInversions++;
                }
            }
        }
        return numberOfInversions;
    }

    // Binary Search Algorithm
    private static int binarySearch(int[] a, int x, int left, int right) {
        if( left > right){
            return -1;
        }
        int mid = (left + right)/2;
        if(x == a[mid]){
            return mid;
        }
        else if(x < a[mid]){
            return binarySearch(a, x, left, mid - 1);
        }
        else{
            return binarySearch(a, x, mid + 1, right);
        }

    }

    // Get Majority Problem Algorithm.
    private static int getMajorityElement(int[] arr) {
        for(int i = 0 ; i < arr.length ; ++i){
            int currentElement = arr[i];
            int count = 0;
            for(int j = 0 ; j < arr.length ; ++j){
                if(arr[j] == currentElement)
                    count++;
            }
            if(count > arr.length / 2){
                return 1;
            }
        }
        return 0;
    }
    private static int getMajorityElementFast(int[] a) {
        a = mergeSort(a);
        int count = 1;
        for(int i = 0; i < a.length -1 ; ++i){
            if(a[i] == a[i+1]){
                count++;
                if (count > a.length / 2){
                    return 1;
                }
            }
            else{
                count = 1;
            }
        }
        return 0;
    }

    // UTIL FUNCTIONS HERE.
    private static int[] cpSubArrayFromArray(int[] arr,int start, int end){
        int[] cpArr = new int[end - start + 1];
        for(int i = 0 ; i < cpArr.length ; ++i){
            cpArr[i] = arr[start + i];
        }
        return cpArr;
    }
    private static int[] copyArray(int[] arr){
        int[] cpArr = new int[arr.length];
        for(int i = 0 ; i < arr.length ; ++i){
            cpArr[i] = arr[i];
        }
        return cpArr;
    }
    private static void printArray(int[] arr){
        for(int i = 0 ; i < arr.length ; ++i){
            System.out.print(arr[i] + " ");
        }
    }
    private static boolean compareArrays(int [] arr1, int[] arr2){
        boolean isEqual = false;
        for(int i = 0 ; i < arr1.length ; ++i){
            if(arr1[i] == arr2[i]){
                isEqual = true;
            }
            else{
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }
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
            int[] res1 = bubbleSort(arr);
            int[] res2 = insertionSort(arr);
            printArray(res1);
            printArray(res2);
            if (!compareArrays(res1, res2)){
                System.out.print("Wrong answer: ");
                printArray(res1);
                System.out.println();
                printArray(res2);
                System.out.println();
                break;
            }
            else{
                System.out.print("OK!\n");
            }
        }
    }
    private static void swapItemsInArray(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Fast Scanner.
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
