package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/9 17:42
 */
public class MaxSum {
    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }

    public static int maxSubArray(int[] nums) {
        return maxSum(nums);
    }

    public static int maxSum(int[] nums){
        if (nums.length == 1){
            return nums[0];
        }
        int max = nums[0];
        int dp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp > 0){
                dp = dp + nums[i];
            }else {
                dp = nums[i];
            }
            max = Math.max(dp,max);
        }
        return max;
    }

}
