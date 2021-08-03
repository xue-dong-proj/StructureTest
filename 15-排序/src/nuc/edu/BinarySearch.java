package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/21 15:05
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {21,54,84,3,65,14,98};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }

        int search = search(arr, 84);
        System.out.println();
        System.out.println(search);
    }

    public static int search(int[] arr,int num){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int begin = 0;
        int end = arr.length;

        while (begin < end){
            int mid = (begin + end) >> 1;
            if(num < arr[mid]){
                end = mid;
            }else if(num > arr[mid]){
                begin = mid + 1;
            }else {
                return mid;
            }
        }

        return -1;
    }
}
