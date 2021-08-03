package nuc.edu;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 薛东
 * @date 2021/4/26 14:50
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {21,54,84,3,65,14,98};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }

        sort(arr);
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "->");
        }
    }

    protected static void sort(int[] array) {
        List<Integer> stepSequence = sedgewickStepSequence(array);
        for (Integer step : stepSequence) {
            sort(array,step);
        }
    }

    /**
     * 分成step列进行排序
     */
    private static void sort(int[] array,int step) {
        // col : 第几列，column的简称
        for (int col = 0; col < step; col++) { // 对第col列进行排序
            // col、col+step、col+2*step、col+3*step
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cur < cur - step) {
                    swap(array,cur, cur - step);
                    cur -= step;
                }
            }
        }
    }

    private static List<Integer> shellStepSequence(int[] array) {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    private static List<Integer> sedgewickStepSequence(int[] array) {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true) {
            if (k % 2 == 0) {
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) {
                break;
            }
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }

    private static void swap(int[] array,int a,int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
