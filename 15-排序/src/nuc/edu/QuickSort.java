package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/26 12:25
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6,11,8,2,9,4,1,5,7,10,3};
        System.out.println("排序前：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "_");
        }
        System.out.println();
        sort(arr);
        System.out.println("排序后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "_");
        }
    }

    protected static void sort(int[] arr){
        sort(arr,0,arr.length);
    }

    private static void sort(int[] arr,int begin,int end){
        if(end - begin < 2){
            return;
        }

        int mid = privotIndex(arr,begin,end);
        sort(arr,begin,mid);
        sort(arr,mid + 1,end);
    }

    private static int privotIndex(int[] arr,int begin,int end){
        int value = arr[begin];
        end--;
        while (begin < end){
            while (begin < end){
                if(value < arr[end]){
                    end--;
                }else {
                    arr[begin] = arr[end];
                    begin++;
                    break;
                }
            }

            while (begin < end) {
                if (value > arr[begin]) {
                    begin++;
                } else {
                    arr[end] = arr[begin];
                    end--;
                    break;
                }
            }
        }

        arr[begin] = value;
        return begin;
    }
}
