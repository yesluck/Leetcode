//
//Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.
// 
//A subsequence of a string S is obtained by deleting 0 or more characters from S.
// 
//A sequence is palindromic if it is equal to the sequence reversed.
// 
//Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
// 
//
// Example 1: 
// 
//Input: 
//S = 'bccb'
//Output: 6
//Explanation: 
//The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
//Note that 'bcb' is counted only once, even though it occurs twice.
// 
// 
//
// Example 2: 
// 
//Input: 
//S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
//Output: 104860361
//Explanation: 
//There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
// 
// 
//
// Note:
// The length of S will be in the range [1, 1000]. 
// Each character S[i] will be in the set {'a', 'b', 'c', 'd'}. 
//

class Solution {
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int kMod = 1000000007;

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                if (S.charAt(i) != S.charAt(j))
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                else {
                    int l = i + 1, r = j - 1;
                    while (l <= r && S.charAt(i) != S.charAt(l))
                        l++;
                    while (l <= r && S.charAt(i) != S.charAt(r))
                        r--;
                    if (l > r)
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    else if (l == r)
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    else
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1];
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + kMod : dp[i][j] % kMod;
            }
        }

        return dp[0][n - 1];
    }
}