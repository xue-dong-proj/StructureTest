package nuc.edu;

/**
 * @author 薛东
 * @date 2021/4/21 19:26
 */
public class MergeSort {
    private int[] arr = {21,54,84,3,65,14,98};
    private int[] leftArray;

    protected  void sort(){
        leftArray = new int[arr.length >> 1];
        sort(0,arr.length);
    }

    private void sort(int begin,int end){
        if(end - begin < 2){
            return;
        }

        int mid = (begin + end) >> 1;
        sort(begin,mid);
        sort(mid,end);
        merge(begin,mid,end);
    }

    private void merge(int begin,int mid,int end){
        int li = 0,le = mid - begin;
        int ri = mid,re = end;
        int ai = begin;

        for (int i = li; i < le; i++){
            leftArray[i] = arr[begin + i];
        }

        while (li < le){
            if(ri < re && leftArray[li] <= arr[ri]){
                arr[ai] = leftArray[li];
                ai++;
                li++;
            }else {
                arr[ai] = arr[ri];
                ai++;
                ri++;
            }
        }
    }

}


