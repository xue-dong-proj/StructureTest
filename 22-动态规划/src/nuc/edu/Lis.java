package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/11 10:13
 */
public class Lis {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7, 101, 18}));
    }

    public static int lengthOfLIS(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int len = 0;
        int[] top = new int[nums.length];
        for (int num : nums) {
            int begin = 0;
            int end = len;
            while (begin < end){
                int mid = (begin + end) >> 1;
                if (top[mid] >= num){
                    end = mid - 1;
                }else {
                    begin = mid + 1;
                }
            }
            top[begin] = num;
            if (begin == len){
                len++;
            }
        }
        return len;
    }

    public static int lengthOfLIS01(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int len = 0;
        int[] top = new int[nums.length];
        for (int num : nums) {
            int j = 0;
            while (j < len){
                if (top[j] >= num){
                    top[j] = num;
                    break;
                }
                j++;
            }
            if (j == len){
                len++;
                top[j] = num;
            }
        }
        return len;
    }
}
