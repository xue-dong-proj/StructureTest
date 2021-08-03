package nuc.edu;

/**
 * @author 薛东
 * @date 2021/5/10 15:28
 */
public class LcsSubArray {
    public static void main(String[] args) {
        System.out.println(lcsSubArray("ABDCBA", "ABBA"));
    }

    public static int lcsSubArray(String str1,String str2){
        if (str1 == null || str2 == null){
            return 0;
        }
        char[] chars1 = str1.toCharArray();
        if (chars1.length == 0){
            return 0;
        }
        char[] chars2 = str2.toCharArray();
        if (chars2.length == 0){
            return 0;
        }
        char[] rowChars = chars1;
        char[] colChars = chars2;
        if (colChars.length > rowChars.length){
            colChars = chars2;
            rowChars = chars1;
        }
        int max = 0;
        int[] dp = new int[colChars.length + 1];
        for (int row = 1; row <= rowChars.length; row++) {
            int cur = 0;
            for (int col = 1; col <= colChars.length; col++) {
                int leftTop = cur;
                cur = dp[col];
                if (chars1[row - 1] != chars2[col - 1]){
                    dp[col] = 0;
                }else {
                    dp[col] = leftTop + 1;
                    dp[col] = Math.max(max,dp[col]);
                }
            }
        }
        return dp[chars2.length];
    }
}
