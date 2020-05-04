//Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'. 
//
// 
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
// 
//
// The matching should cover the entire input string (not partial). 
//
// Note: 
//
// 
// s could be empty and contains only lowercase letters a-z. 
// p could be empty and contains only lowercase letters a-z, and characters like . or *. 
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
//p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
// 
//
// Example 3: 
//
// 
//Input:
//s = "ab"
//p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
// 
//
// Example 4: 
//
// 
//Input:
//s = "aab"
//p = "c*a*b"
//Output: true
//Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
// 
//
// Example 5: 
//
// 
//Input:
//s = "mississippi"
//p = "mis*is*p*."
//Output: false
// 
//

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        if(s == null || p == null)  return false;
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        // from here on, the indexes should be the position in the string! not the boolean[][]
        for(int i = 0; i < n; i++) {
            if(p.charAt(i) == '*') {
                if(i > 0)
                    dp[0][i+1] = dp[0][i-1];
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(s.charAt(i) == p.charAt(j))
                    dp[i+1][j+1] = dp[i][j];
                if(p.charAt(j) == '.')
                    dp[i+1][j+1] = dp[i][j];
                // note: j > 0 !!!
                if(j > 0 && p.charAt(j) == '*') {
                    if(s.charAt(i) != p.charAt(j-1) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }
                    else {
                        dp[i+1][j+1] = (dp[i+1][j-1] || dp[i+1][j] || dp[i][j+1]);
                    }
                }
            }
        }
        return dp[m][n];
    }
}


// ~44
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int j = 1; j < n; j++)             // change 1.1: 0->1
            if (p.charAt(j) == '*')
                dp[0][j + 1] = dp[0][j - 1];    // change 1.2: j -> j-1

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && p.charAt(j) == '*') {  // main change 2: dealing with '*'
                    if (s.charAt(i) != p.charAt(j - 1) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                    else {
                        dp[i + 1][j + 1] = (dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1]);
                    }
                }
                else
                    dp[i + 1][j + 1] = dp[i][j] && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
            }
        }

        return dp[m][n];
    }
}