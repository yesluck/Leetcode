//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// You are given a target value to search. If found in the array return its index, otherwise return -1. 
//
// You may assume no duplicate exists in the array. 
//
// Your algorithm's runtime complexity must be in the order of O(log n). 
//
// Example 1: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1 
//

class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0)    return -1;
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            // every number in the left part of offset is larger than every number in the right part of offset
            // nums[right] > nums[mid] -> increasing in the right half, offset in the left half
            // nums[right] < nums[mid] -> increasing in the left half, offset in the right half
            // Please remember must compare nums[right] and nums[mid]!!!
            if(nums[right] < nums[mid]) left = mid + 1;
            else    right = mid;
        }
        int offset = left;
        left = 0; right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int realMid = (mid + offset) % nums.length;
            if(nums[realMid] == target) return realMid;
            else if(nums[realMid] < target) left = mid + 1;
            else    right = mid;
        }
        int realIdx = (left + offset) % nums.length;
        return nums[realIdx] == target ? realIdx : -1;
    }
}


// one-pass binary search
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return -1;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < nums[right]) {
                // which means the right part is sorted
                // then just need to judge if in the right part
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid;
            } else {
                // left part sorted, judge if in the left part
                if (target >= nums[left] && target < nums[mid])
                    right = mid;
                else
                    left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;    // !!!
    }
}