package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/21 15:21
 */
public class InsortBinarySort {
    public static void main(String[] args) {
        int[] arr = {21,54,84,3,65,14,98};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }

        for(int begin = 1; begin < arr.length; begin++){
            int value = arr[begin];
            int beginIndex = search(arr,begin);

            for(int i = begin; i > beginIndex; i--){
                arr[i] = arr[i - 1];
            }

            arr[beginIndex] = value;
        }

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }

    }

    public static int search(int[] arr,int index){
        int begin = 0;
        int end = index;

        while (begin < end){
            int mid = (begin + end) >> 1;

            if(arr[index] < arr[mid]){
                end = mid;
            }else {
                begin = mid + 1;
            }
        }

        return begin;
    }
}
