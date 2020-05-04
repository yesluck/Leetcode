//Alex and Lee play a game with piles of stones. There are an even number of pil
//es arranged in a row, and each pile has a positive integer number of stones pile
//s[i]. 
//
// The objective of the game is to end with the most stones. The total number of
// stones is odd, so there are no ties. 
//
// Alex and Lee take turns, with Alex starting first. Each turn, a player takes 
//the entire pile of stones from either the beginning or the end of the row. This 
//continues until there are no more piles left, at which point the person with the
// most stones wins. 
//
// Assuming Alex and Lee play optimally, return True if and only if Alex wins th
//e game. 
//
// 
//
// Example 1: 
//
// 
//Input: [5,3,4,5]
//Output: true
//Explanation: 
//Alex starts first, and can only take the first 5 or the last 5.
//Say he takes the first 5, so that the row becomes [3, 4, 5].
//If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 poin
//ts.
//If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win wit
//h 9 points.
//This demonstrated that taking the first 5 was a winning move for Alex, so we r
//eturn true.
// 
//
// 
//
// Note: 
//
// 
// 2 <= piles.length <= 500 
// piles.length is even. 
// 1 <= piles[i] <= 500 
// sum(piles) is odd. 
// Related Topics Math Dynamic Programming Minimax


// 区间DP
class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = piles[i];

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] > 0;
    }
}


// directly
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}