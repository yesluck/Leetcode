//In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue. 
//
// For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s. 
//
// Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once. 
//
// Note: 
//
// 
// The given numbers of 0s and 1s will both not exceed 100 
// The size of given string array won't exceed 600. 
// 
//
// 
//
// Example 1: 
//
// 
//Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
//Output: 4
//
//Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
// 
//
// 
//
// Example 2: 
//
// 
//Input: Array = {"10", "0", "1"}, m = 1, n = 1
//Output: 2
//
//Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
// 
//
// 
//

// 3-d time and space
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] usage = new int[len][2];

        for (int i = 0; i < len; i++) {
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0')
                    usage[i][0] += 1;
                else
                    usage[i][1] += 1;
            }
        }

        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= usage[i - 1][0] && k >= usage[i - 1][1])
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - usage[i - 1][0]][k - usage[i - 1][1]] + 1);
                    else
                        dp[i][j][k] = dp[i - 1][j][k];
                }
            }
        }

        return dp[len][m][n];
    }
}


// 3-d time and 2-d space
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] usage = new int[len][2];

        for (int i = 0; i < len; i++) {
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0')
                    usage[i][0] += 1;
                else
                    usage[i][1] += 1;
            }
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            for (int j = m; j >= usage[i - 1][0]; j--) {
                for (int k = n; k >= usage[i - 1][1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - usage[i - 1][0]][k - usage[i - 1][1]] + 1);
                }
            }
        }

        return dp[m][n];
    }
}