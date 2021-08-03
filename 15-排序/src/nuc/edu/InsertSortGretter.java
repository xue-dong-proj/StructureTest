package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/21 14:51
 */
public class InsertSortGretter {
    public static void main(String[] args) {
        int[] arr = {21,54,84,3,65,14,98};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }

        for(int begin = 1; begin < arr.length; begin++){
            int cur = begin;
            int value = arr[cur];

            while (cur > 0 && value < arr[cur - 1]){
                arr[cur] = arr[cur - 1];
                cur--;
            }

            arr[cur] = value;
        }

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
    }
}
