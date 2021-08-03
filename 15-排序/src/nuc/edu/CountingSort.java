package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/28 19:09
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {7,3,5,8,6,7,4,5};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
        System.out.println();
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
    }

    public static void sort(int[] arr){
        int max = arr[0];
        int min = arr[0];

        // 找出数组中的最大值和最小值
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
            if(arr[i] < min){
                min = arr[i];
            }
        }

        // 然后我们就可以确定数组的大小范围了
        int[] counts = new int[max - min + 1];

        // 我们把arr中的数组元素存放在counts数组中
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] - min]++;
        }

        for(int i = 1; i < counts.length; i++){
            counts[i] += counts[i - 1];
        }

        int[] newArray = new int[arr.length];

        for(int i = arr.length - 1; i >= 0; i--){
            newArray[--counts[arr[i] - min]] = arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArray[i];
        }
    }
}
