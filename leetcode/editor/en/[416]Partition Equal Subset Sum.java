//Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal. 
//
// Note: 
//
// 
// Each of the array element will not exceed 100. 
// The array size will not exceed 200. 
// 
//
// 
//
// Example 1: 
//
// 
//Input: [1, 5, 11, 5]
//
//Output: true
//
//Explanation: The array can be partitioned as [1, 5, 5] and [11].
// 
//
// 
//
// Example 2: 
//
// 
//Input: [1, 2, 3, 5]
//
//Output: false
//
//Explanation: The array cannot be partitioned into equal sum subsets.
// 
//
// 
//

// O(NV) time + space
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += nums[i];
        if (sum % 2 == 1)
            return false;
        int V = sum / 2;
        boolean[][] dp = new boolean[n + 1][V + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = nums[i - 1]; j <= V; j++) {
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];  // nums[i - 1]为第i个数
            }
        }

        return dp[n][V];
    }
}


// O(V) space
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += nums[i];
        if (sum % 2 == 1)
            return false;
        int V = sum / 2;
        boolean[] dp = new boolean[V + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = V; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];  // nums[i - 1]为第i个数
            }
        }

        return dp[V];
    }
}