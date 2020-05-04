//
//There is a strange printer with the following two special requirements:
//
// 
// The printer can only print a sequence of the same character each time. 
// At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters. 
// 
//
// 
//
// 
//Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
// 
//
// Example 1: 
// 
//Input: "aaabbb"
//Output: 2
//Explanation: Print "aaa" first and then print "bbb".
// 
// 
//
// Example 2: 
// 
//Input: "aba"
//Output: 2
//Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
// 
// 
//
// Hint: Length of the given string will not exceed 100.

class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        if (n == 0)
            return 0;

        int[][] dp = new int[n][n];

        for (int len = 0; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                if (len == 0)
                    dp[i][j] = 1;
                else if (len == 1)
                    dp[i][j] = (s.charAt(i) == s.charAt(j) ? 1 : 2);
                else {
                    dp[i][j] = j - i + 1;
                    for (int k = i; k < j; k++)
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    if (s.charAt(i) == s.charAt(j))
                        dp[i][j] -= 1;  // if s[i] == s[j], we can print from i to j first
                }
            }
        }

        return dp[0][n - 1];
    }
}