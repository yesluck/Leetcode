//You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night. 
//
// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police. 
//
// Example 1: 
//
// 
//Input: [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
//             because they are adjacent houses.
// 
//
// Example 2: 
//
// 
//Input: [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//             Total amount you can rob = 1 + 3 = 4. 
//

// DP using extra 1d array
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp0 = new int[n], dp1 = new int[n];   // dp0: start from house[0], dp1: start from house[1]

        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];

        dp0[0] = nums[0];
        dp0[1] = Math.max(nums[0], nums[1]);
        dp1[1] = nums[1];
        for (int i = 2; i < n; i++) {
            dp0[i] = Math.max(dp0[i - 2] + nums[i], dp0[i - 1]);
            dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]);
        }

        return Math.max(dp0[n - 2], dp1[n - 1]);
    }
}


// DP using extra 4 variables
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];

        int prevMax0 = 0, currMax0 = 0;
        int prevMax1 = 0, currMax1 = 0;

        for (int i = 0; i < n - 1; i++) {
            int temp = currMax0;
            currMax0 = Math.max(prevMax0 + nums[i], currMax0);
            prevMax0 = temp;
        }

        for (int i = 1; i < n; i++) {
            int temp = currMax1;
            currMax1 = Math.max(prevMax1 + nums[i], currMax1);
            prevMax1 = temp;
        }

        return Math.max(currMax0, currMax1);
    }
}