//Given an unsorted integer array, find the smallest missing positive integer. 
//
// Example 1: 
//
// 
//Input: [1,2,0]
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: [3,4,-1,1]
//Output: 2
// 
//
// Example 3: 
//
// 
//Input: [7,8,9,11,12]
//Output: 1
// 
//
// Note: 
//
// Your algorithm should run in O(n) time and uses constant extra space. 
//

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int idx = 0;
        while(idx < n) {
            int newIdx = nums[idx] - 1;
            if(nums[idx] > 0 && idx != newIdx && newIdx >= 0 && newIdx < n && nums[idx] != nums[newIdx]) {
                swap(nums, idx, newIdx);
            }
            else    idx++;
        }
        for(int i = 0; i < n; i++)
            if(nums[i] != i+1)  return i+1;
        return n+1;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}