//Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution. 
//
// Example: 
//
// 
//Given array nums = [-1, 2, 1, -4], and target = 1.
//
//The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
// 
//

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minus = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                if(nums[i] + nums[left] + nums[right] == target)    return target;  // improve 1
                else {
                    int temp = nums[i] + nums[left] + nums[right] - target;
                    if(Math.abs(temp) < Math.abs(minus))    minus = temp;
                    if(nums[i] + nums[left] + nums[right] < target)    left++;
                    else    right--;
                }
            }
        }
        return target + minus;
    }
}

// with one more improvement (~ avoid dulpicate like [15])
class Solution2 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);  // remember!!!
        int minus = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                if(nums[i] + nums[left] + nums[right] == target)    return target;  // improve 1
                else {
                    int temp = nums[i] + nums[left] + nums[right] - target;
                    if(Math.abs(temp) < Math.abs(minus))    minus = temp;
                    if(nums[i] + nums[left] + nums[right] < target) {
                        while(left < right && nums[left] == nums[left + 1]) left++; //improve 2(1)
                        left++;
                    }
                    else {
                        while(left < right && nums[right] == nums[right - 1])   right--;    // improve 2(2)
                        right--;
                    }
                }
            }
        }
        return target + minus;
    }
}