//
//You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
// 
//
// Find out how many ways to assign symbols to make sum of integers equal to target S. 
// 
//
// Example 1: 
// 
//Input: nums is [1, 1, 1, 1, 1], S is 3. 
//Output: 5
//Explanation: 
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//There are 5 ways to assign symbols to make the sum of nums be target 3.
// 
// 
//
// Note: 
// 
// The length of the given array is positive and will not exceed 20. 
// The sum of elements in the given array will not exceed 1000. 
// Your output answer is guaranteed to be fitted in a 32-bit integer. 
// 
//

// O(NV): 较为繁琐
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += nums[i];
        if (sum < S || (S + sum) % 2 == 1)
            return 0;
        int V = (S + sum) / 2;
        int[][] dp = new int[n + 1][V + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= V; j++)
                dp[i][j] = dp[i - 1][j];
            for (int j = nums[i - 1]; j <= V; j++) {
                dp[i][j] += dp[i - 1][j - nums[i - 1]];   // nums[i - 1]为第i个数
            }
        }

        return dp[n][V];
    }
}


// O(V) space
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += nums[i];
        if (sum < S || (S + sum) % 2 == 1)
            return 0;
        int V = (S + sum) / 2;
        int[] dp = new int[V + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = V; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] + dp[j - nums[i - 1]];    // nums[i - 1]为第i个数
            }
        }

        return dp[V];
    }
}