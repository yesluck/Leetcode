//Given an integer array nums and an integer k, return the kth largest element i
//n the array. 
//
// Note that it is the kth largest element in the sorted order, not the kth dist
//inct element. 
//
// 
// Example 1: 
// Input: nums = [3,2,1,5,6,4], k = 2
//Output: 5
// Example 2: 
// Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
//Output: 4
// 
// 
// Constraints: 
//
// 
// 1 <= k <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// 
// Related Topics Divide and Conquer Heap


// Quick Select
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, n - k + 1);
    }

    public int quickSelect(int[] nums, int left, int right, int k) {
        int lo = left, hi = right;

        // Randomize pivot
        int pivotIdx = left + (int)(Math.random() * (right - left + 1));
        int pivot = nums[pivotIdx];
        nums[pivotIdx] = nums[lo];
        nums[lo] = pivot;

        while (lo < hi) {
            while (lo < hi && nums[hi] >= pivot)
                hi--;
            nums[lo] = nums[hi];
            while (lo < hi && nums[lo] <= pivot)
                lo++;
            nums[hi] = nums[lo];
        }
        nums[lo] = pivot;

        int pivotRank = lo - left + 1;
        if (pivotRank == k)
            return pivot;
        else if (pivotRank > k)
            return quickSelect(nums, left, lo, k);
        else
            return quickSelect(nums, lo + 1, right, k - pivotRank);
    }
}
