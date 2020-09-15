//Given an array nums of 0s and 1s and an integer k, return True if all 1's are 
//at least k places away from each other, otherwise return False. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: nums = [1,0,0,0,1,0,0,1], k = 2
//Output: true
//Explanation: Each of the 1s are at least 2 places away from each other.
// 
//
// Example 2: 
//
// 
//
// 
//Input: nums = [1,0,0,1,0,1], k = 2
//Output: false
//Explanation: The second 1 and third 1 are only one apart from each other. 
//
// Example 3: 
//
// 
//Input: nums = [1,1,1,1,1], k = 0
//Output: true
// 
//
// Example 4: 
//
// 
//Input: nums = [0,1,0,1], k = 1
//Output: true
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// 0 <= k <= nums.length 
// nums[i] is 0 or 1 
// 
// Related Topics Array


// My answer using Sliding Window (what a stupid answer...) [0:05:12 - 0:07:37]
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int winSum = 0;
        int n = nums.length;
        for (int i = 0; i < k + 1; i++) {
            winSum += nums[i];
            if (winSum > 1)
                return false;
        }

        for (int i = k + 1; i < n; i++) {
            winSum -= nums[i - k - 1];
            winSum += nums[i];
            if (winSum > 1)
                return false;
        }

        return true;
    }
}


// One pass by memorizing the previos idx of 1
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        int prev1 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (prev1 != -1 && (i - prev1 - 1 < k))
                    return false;
                prev1 = i;
            }
        }
        return true;
    }
}