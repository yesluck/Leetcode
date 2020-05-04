//Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product. 
//
// Example 1: 
//
// 
//Input: [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.
// 
//
// Example 2: 
//
// 
//Input: [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray. 
//

class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        int currMax = nums[0], currMin = nums[0], maxProd = nums[0], minProd = nums[0];
        for (int i = 1; i < n; i++) {
            int temp = currMax;
            currMax = Math.max(Math.max(nums[i], currMax * nums[i]), currMin * nums[i]);
            currMin = Math.min(Math.min(nums[i], currMin * nums[i]), temp * nums[i]);
            maxProd = Math.max(currMax, maxProd);
        }

        return maxProd;
    }
}