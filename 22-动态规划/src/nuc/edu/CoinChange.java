package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/9 9:06
 */
public class CoinChange {
    public static void main(String[] args) {
        // System.out.println(coins(41));
        System.out.println(coins1(41));
        int[] faces = {1,5,20,25};
        System.out.println(coins2(41,faces));
    }

    public static int coins(int n){
        if (n < 1){
            return Integer.MAX_VALUE;
        }
        if(n == 25 || n ==20 || n == 5 || n == 1){
            return 1;
        }
        int min1 = Math.min(coins(n - 25),coins(n - 20));
        int min2 = Math.min(coins(n - 5),coins(n - 1));
        return Math.min(min1,min2) + 1;
    }

    public static int coins1(int n){
        if (n < 1){
            return -1;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            if (i >= 1){
                min = Math.min(min,dp[i - 1]);
            }
            if (i >= 5){
                min = Math.min(min,dp[i - 5]);
            }
            if (i >= 20){
                min = Math.min(min,dp[i - 20]);
            }
            if (i >= 25){
                min = Math.min(min,dp[i - 25]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    public static int coins2(int n,int[] faces){
        if (n < 1 || faces == null || faces.length == 0){
            return -1;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                if (i < face){
                    continue;
                }
                min = Math.min(min,dp[i - face]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }
}
