package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/10 16:53
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 10;
        System.out.println(knapsack(weights, values, capacity));
    }

    public static int knapsack(int[] weights,int[] values,int capacity){
        if (weights == null || weights.length == 0){
            return 0;
        }
        if (values == null || values.length == 0){
            return 0;
        }
        if (capacity <= 0){
            return 0;
        }
        if (weights.length != values.length){
            return 0;
        }
        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j],values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return dp[values.length][capacity];
    }
}
