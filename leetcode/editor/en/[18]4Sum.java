//Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target. 
//
// Note: 
//
// The solution set must not contain duplicate quadruplets. 
//
// Example: 
//
// 
//Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
//
//A solution set is:
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
//

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }
    private List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
        int n = nums.length;
        if(n == 0)  return new ArrayList<>();
        int max = nums[n - 1];
        if(nums[start] * k > target || max * k < target)    return new ArrayList<>();   // improvement 3 (4ms -> 3ms)
        List<List<Integer>> res = new ArrayList<>();
        if(k == 2) {
            int left = start, right = n - 1;
            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum == target) {
                    List<Integer> temp2 = new ArrayList<>();
                    temp2.add(nums[left]);
                    temp2.add(nums[right]);
                    res.add(temp2);
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1])   right--;
                    left++;
                    right--;
                }
                else if(sum < target)   left++;
                else    right--;
            }
        }
        else {
            for(int i = start; i < n - (k-1); i++) {
                if(i > start && nums[i] == nums[i-1])   continue;
                if(nums[i] + max * (k - 1) < target)    continue;   // improvement 1 (37ms -> 15ms)
                if(nums[i] * k > target)    break;                  // improvement 2 (15ms -> 4ms)
                List<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1);
                for(List<Integer> t:temp) {
                    t.add(nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }
}