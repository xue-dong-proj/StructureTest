package nuc.edu;

import java.util.Stack;

/**
 * @author 薛东
 * @date 2021/4/28 21:05
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {129,69,593,23,6,89,54,8};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
        System.out.println();
        sortRadix(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
    }

    public static void sortRadix(int[] arr){
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }

        for(int divider = 1; divider <= max; divider *= 10){
            sort(arr,divider);
        }
    }

    public static void sort(int[] arr, int divider){
        int[] counts = new int[10];

        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] / divider % 10]++;
        }

        for (int i = 1; i < counts.length; i++){
            counts[i] += counts[i - 1];
        }

        int[] newArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            newArray[--counts[arr[i] / divider % 10]] = arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArray[i];
        }
    }
}
