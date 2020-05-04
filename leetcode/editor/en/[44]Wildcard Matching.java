//Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'. 
//
// 
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
// 
//
// The matching should cover the entire input string (not partial). 
//
// Note: 
//
// 
// s could be empty and contains only lowercase letters a-z. 
// p could be empty and contains only lowercase letters a-z, and characters like ? or *. 
// 
//
// Example 1: 
//
// 
//Input:
//s = "aa"
//p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
// 
//
// Example 2: 
//
// 
//Input:
//s = "aa"
//p = "*"
//Output: true
//Explanation: '*' matches any sequence.
// 
//
// Example 3: 
//
// 
//Input:
//s = "cb"
//p = "?a"
//Output: false
//Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
// 
//
// Example 4: 
//
// 
//Input:
//s = "adceb"
//p = "*a*b"
//Output: true
//Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
// 
//
// Example 5: 
//
// 
//Input:
//s = "acdcb"
//p = "a*c?b"
//Output: false
// 
//

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int j = 0; j < n; j++)
            if (p.charAt(j) == '*')
                dp[0][j + 1] = dp[0][j];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p.charAt(j) == '*')
                    dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j];
                else
                    dp[i + 1][j + 1] = dp[i][j] && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i));
            }
        }

        return dp[m][n];
    }
}