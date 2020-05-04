//Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2. 
//
// Example 1: 
//
// 
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//Output: false
// 
//

// recursion with memorization
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                memo[i][j] = -1;
        return helper(s1, 0, s2, 0, s3, 0, memo);
    }

    public boolean helper(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        int m = s1.length(), n = s2.length(), p = s3.length();
        if (i == m)
            return s2.substring(j, n).equals(s3.substring(k, p));
        if (j == n)
            return s1.substring(i, m).equals(s3.substring(k, p));
        if (memo[i][j] >= 0)
            return memo[i][j] == 1 ? true : false;

        boolean res = false;
        if ((s1.charAt(i) == s3.charAt(k) && helper(s1, i + 1, s2, j, s3, k + 1, memo)) || (s2.charAt(j) == s3.charAt(k) && helper(s1, i, s2, j + 1, s3, k + 1, memo)))
            res = true;
        memo[i][j] = res ? 1 : 0;
        return res;
    }
}


// 2-d dp
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), p = s3.length();
        if (m + n != p)
            return false;
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j != 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                if (i != 0 && j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                if (i != 0 && j != 0)
                    dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[m][n];
    }
}


// 1-d dp
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), p = s3.length();
        if (m + n != p)
            return false;
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j != 0)
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                if (i != 0 && j == 0)
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                if (i != 0 && j != 0)
                    dp[j] = (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n];
    }
}