package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/19 17:26
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {21,54,84,3,65,14,98};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }

        for (int end = arr.length - 1; end > 0; end--){
            int maxIndex = 0;
            for(int begin = 1; begin <= end; begin++){
                if(arr[maxIndex] <= arr[begin]){
                    maxIndex = begin;
                }
            }

            int temp = arr[maxIndex];
            arr[maxIndex] = arr[end];
            arr[end] = temp;
        }

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
    }
}
