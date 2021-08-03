package nuc.edu;

import java.util.Arrays;

/**
 * @author 薛东
 * @date 2021/5/8 17:21
 */
public class Pirate {
    public static void main(String[] args) {
        int[] arr = new int[]{3,5,4,10,7,14,2,11};
        pirate(arr);
    }

    public static void pirate(int[] arr){
        int capacity = 30;
        int weight = 0;
        int count = 0;
        Arrays.sort(arr);
        System.out.println("选择的古董为：");
        for (int i = 0; i < arr.length; i++) {
            int newWeight = weight + arr[i];
            if (newWeight <= capacity){
                weight = newWeight;
                count++;
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
        System.out.println("一共选择的古董个数为：" + count);
    }
}
