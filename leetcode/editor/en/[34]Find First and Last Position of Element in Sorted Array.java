//Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value. 
//
// Your algorithm's runtime complexity must be in the order of O(log n). 
//
// If the target is not found in the array, return [-1, -1]. 
//
// Example 1: 
//
// 
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4] 
//
// Example 2: 
//
// 
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1] 
//

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n == 0 || nums[0] > target || nums[n-1] < target)    return new int[]{-1, -1};
        int[] res = new int[2];
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target)  left = mid + 1;
            else    right = mid;
        }
        if(nums[left] == target)    res[0] = left;
        else    return new int[]{-1, -1};
        right = n-1;
        while(left < right) {
            int mid = left + (right - left) / 2 + 1;    // !!!
            if(nums[mid] > target)  right = mid - 1;    // !!!
            else    left = mid;                         // !!!
        }
        res[1] = right;
        return res;
    }
}