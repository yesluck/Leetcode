//Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set). 
//
// Note: The solution set must not contain duplicate subsets. 
//
// Example: 
//
// 
//Input: [1,2,2]
//Output:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
// 
//

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), new boolean[nums.length], nums);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> temp, boolean[] used, int[] nums) {
        if(temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1])  continue;
            temp.add(nums[i]);
            used[i] = true;
            backtracking(res, temp, used, nums);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}