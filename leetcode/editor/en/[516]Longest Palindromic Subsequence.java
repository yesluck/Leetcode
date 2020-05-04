//
//Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
// 
//
// Example 1: 
//Input: 
// 
//"bbbab"
// 
//Output: 
// 
//4
// 
//One possible longest palindromic subsequence is "bbbb".
// 
//
// Example 2: 
//Input:
// 
//"cbbd"
// 
//Output:
// 
//2
// 
//One possible longest palindromic subsequence is "bb".
//

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }
}


// use (len, i) loop
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }
}