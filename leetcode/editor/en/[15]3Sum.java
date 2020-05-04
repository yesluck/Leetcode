//Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero. 
//
// Note: 
//
// The solution set must not contain duplicate triplets. 
//
// Example: 
//
// 
//Given array nums = [-1, 0, 1, 2, -1, -4],
//
//A solution set is:
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
//

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i-1])   continue;
            int remain = - nums[i];
            int left = i+1, right = nums.length - 1;
            while(left < right) {
                if(nums[left] + nums[right] == remain) {
                    List<Integer> ls = new ArrayList<>();
                    ls.add(nums[i]);
                    ls.add(nums[left]);
                    ls.add(nums[right]);
                    res.add(ls);
                    // really important (to avoid duplicate)
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1])   right--;
                    left++;
                    right--;
                }
                else if(nums[left] + nums[right] > remain) {
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return res;
    }
}