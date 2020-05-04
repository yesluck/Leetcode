//Say you have an array for which the ith element is the price of a given stock on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most two transactions. 
//
// Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3. 
//
// Example 2: 
//
// 
//Input: [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
//             engaging multiple transactions at the same time. You must sell before buying again.
// 
//
// Example 3: 
//
// 
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0. 
//

// 2-d DP from 188
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        int[][] dp = new int[3][n];
        for (int i = 1; i <= 2; i++) {
            int maxTemp = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], maxTemp + prices[j]);
                maxTemp = Math.max(maxTemp, -prices[j] + dp[i - 1][j - 1]);
            }
        }

        return dp[2][n - 1];
    }
}


// bidirectional DP
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        int[] leftProfits = new int[n], rightProfits = new int[n + 1];
        int leftMin = prices[0], rightMax = prices[n - 1];
        int res = 0;

        for (int i = 1; i < n; i++) {
            int left = i, right = n - i - 1;

            leftProfits[left] = Math.max(leftProfits[left - 1], prices[left] - leftMin);
            leftMin = Math.min(leftMin, prices[left]);

            rightProfits[right] = Math.max(rightProfits[right + 1], rightMax - prices[right]);
            rightMax = Math.max(rightMax, prices[right]);
        }

        for (int i = 0; i < n; i++)
            res = Math.max(res, leftProfits[i] + rightProfits[i + 1]);

        return res;
    }
}