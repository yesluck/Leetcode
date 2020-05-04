//Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent. 
//
// Find the maximum coins you can collect by bursting the balloons wisely. 
//
// Note: 
//
// 
// You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them. 
// 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100 
// 
//
// Example: 
//
// 
//Input: [3,1,5,8]
//Output: 167 
//Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
//

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] new_nums = new int[n];
        new_nums[0] = new_nums[n - 1] = 1;
        for (int i = 1; i < n - 1; i++)
            new_nums[i] = nums[i - 1];

        int[][] dp = new int[n][n];

        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MIN_VALUE;
                for (int k = i + 1; k < j; k++)
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + new_nums[i] * new_nums[k] * new_nums[j]);
            }
        }

        return dp[0][n - 1];
    }
}