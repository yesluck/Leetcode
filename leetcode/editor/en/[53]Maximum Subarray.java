//Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum. 
//
// Example: 
//
// 
//Input: [-2,1,-3,4,-1,2,1,-5,4],
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.
// 
//
// Follow up: 
//
// If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle. 
//

// Greedy
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int local = nums[0], global = nums[0];
        for(int i = 1; i < n; i++) {
            local = Math.max(local + nums[i], nums[i]);
            global = Math.max(local, global);
        }
        return global;
    }
}


// DP (Kadane's Algo)
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0;
        int res = nums[0];

        for(int i = 1; i < n; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
                res = Math.max(res, nums[i]);
            }
        }
        return res;
    }
}