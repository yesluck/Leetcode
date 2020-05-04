//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand. 
//
// (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]). 
//
// You are given a target value to search. If found in the array return true, otherwise return false. 
//
// Example 1: 
//
// 
//Input: nums = [2,5,6,0,0,1,2], target = 0
//Output: true
// 
//
// Example 2: 
//
// 
//Input: nums = [2,5,6,0,0,1,2], target = 3
//Output: false 
//
// Follow up: 
//
// 
// This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates. 
// Would this affect the run-time complexity? How and why? 
// 
//

class Solution {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return false;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] < nums[right]) {
                // which means the right part is sorted
                // then just need to judge if in the right part
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid;
            } else if (nums[mid] > nums[right]) {
                // left part sorted, judge if in the left part
                if (target >= nums[left] && target < nums[mid])
                    right = mid;
                else
                    left = mid + 1;
            } else
                right--;
        }
        return nums[left] == target ? true : false;
    }
}