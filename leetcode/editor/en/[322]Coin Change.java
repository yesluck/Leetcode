//You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1. 
//
// Example 1: 
//
// 
//Input: coins = [1, 2, 5], amount = 11
//Output: 3 
//Explanation: 11 = 5 + 5 + 1 
//
// Example 2: 
//
// 
//Input: coins = [2], amount = 3
//Output: -1
// 
//
// Note: 
//You may assume that you have an infinite number of each kind of coin. 
//


// 2-d
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        int count = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = amount + 1;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = amount; j >= 1; j--) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k <= j / coins[i - 1]; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * coins[i - 1]] + k);
            }
        }

        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }
}

// 1-d
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        int count = 0;

        dp[0] = 0;
        for (int i = 1; i <= amount; i++)
            dp[i] = amount + 1;

        for (int i = 1; i <= n; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}