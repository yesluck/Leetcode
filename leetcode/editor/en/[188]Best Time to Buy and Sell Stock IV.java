//Say you have an array for which the i-th element is the price of a given stock on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most k transactions. 
//
// Note: 
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [2,4,1], k = 2
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
// 
//
// Example 2: 
//
// 
//Input: [3,2,6,5,0,3], k = 2
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
//             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
// 
//

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2)
            return quickSolve(prices);

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int maxTemp = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], maxTemp + prices[j]);
                maxTemp = Math.max(maxTemp, -prices[j] + dp[i - 1][j - 1]);
            }
        }

        return dp[k][n - 1];
    }

    public int quickSolve(int[] prices) {
        int n = prices.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] - prices[i - 1] > 0)
                res += prices[i] - prices[i - 1];
        }
        return res;
    }
}