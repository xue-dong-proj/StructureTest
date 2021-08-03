package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/19 16:58
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {21,54,84,3,65,14,98};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }

        for(int end = arr.length - 1; end > 0; end--){
            for(int begin = 1; begin <= end; begin++){
                if(arr[begin] < arr[begin - 1]){
                    int temp = arr[begin];
                    arr[begin] = arr[begin - 1];
                    arr[begin - 1] = temp;
                }
            }
        }

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
    }
}

