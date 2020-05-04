//Given a set of distinct integers, nums, return all possible subsets (the power set). 
//
// Note: The solution set must not contain duplicate subsets. 
//
// Example: 
//
// 
//Input: nums = [1,2,3]
//Output:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
//

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        Arrays.sort(nums);
        backtracking(res, temp, nums, 0);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums, int pos) {
        res.add(new ArrayList<Integer>(temp));
        for (int i = pos; i < nums.length; i++) {
            temp.add(nums[i]);
            backtracking(res, temp, nums, i+1);
            temp.remove(temp.size() - 1);
        }
    }
}