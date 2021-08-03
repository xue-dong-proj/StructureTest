package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/8 22:18
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        return maxSubArray(nums,0,nums.length);
    }

    public static int maxSubArray(int[] nums,int begin,int end){
        if (end - begin < 2){
            return nums[begin];
        }
        int mid = (end + begin) >> 1;
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--){
            leftSum += nums[i];
            leftMax = Math.max(leftMax,leftSum);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++){
            rightSum += nums[i];
            rightMax = Math.max(rightMax,rightSum);
        }

        return Math.max(leftMax + rightMax,
                Math.max(maxSubArray(nums,begin,mid),
                        maxSubArray(nums,mid,end)));
    }

}
