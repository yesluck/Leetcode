//You are given a sorted array consisting of only integers where every element a
//ppears exactly twice, except for one element which appears exactly once. Find th
//is single element that appears only once. 
//
// Follow up: Your solution should run in O(log n) time and O(1) space. 
//
// 
// Example 1: 
// Input: nums = [1,1,2,3,3,4,4,8,8]
//Output: 2
// Example 2: 
// Input: nums = [3,3,7,7,10,11,11]
//Output: 10
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// 0 <= nums[i] <= 10^5 
// Related Topics Binary Search


class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean halvesAreEven = (right - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}


// Only check even indexes
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1)
                mid--;
            if (nums[mid] == nums[mid + 1])
                left = mid + 2;
            else
                right = mid;
        }
        return nums[left];
    }
}
