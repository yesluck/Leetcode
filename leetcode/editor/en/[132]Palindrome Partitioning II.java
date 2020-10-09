//Given a string s, partition s such that every substring of the partition is a 
//palindrome. 
//
// Return the minimum cuts needed for a palindrome partitioning of s. 
//
// 
// Example 1: 
//
// 
//Input: s = "aab"
//Output: 1
//Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 
//cut.
// 
//
// Example 2: 
//
// 
//Input: s = "a"
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: s = "ab"
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 2000 
// s consists of lower-case English letters only. 
// 
// Related Topics Dynamic Programming


class Solution {
    public int minCut(String s) {
        int n = s.length();

        if (n == 1)
            return 0;

        boolean[][] valid = new boolean[n][n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++)
            Arrays.fill(valid[i], true);
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                valid[i][j] = s.charAt(i) == s.charAt(j) && valid[i + 1][j - 1];
            }
        }

        Arrays.fill(dp, n);
        for (int i = 0; i < n; i++) {
            if (valid[0][i]) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (valid[j + 1][i])
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }

        return dp[n - 1];
    }
}