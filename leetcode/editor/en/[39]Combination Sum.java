//Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target. 
//
// The same repeated number may be chosen from candidates unlimited number of times. 
//
// Note: 
//
// 
// All numbers (including target) will be positive integers. 
// The solution set must not contain duplicate combinations. 
// 
//
// Example 1: 
//
// 
//Input: candidates = [2,3,6,7], target = 7,
//A solution set is:
//[
//  [7],
//  [2,2,3]
//]
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,3,5], target = 8,
//A solution set is:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//]
// 
//

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtracking(res, temp, 0, candidates, target);
        return res;
    }
    private void backtracking(List<List<Integer>> res, List<Integer> temp, int pos, int[] candidates, int remain) {
        if(remain == 0) {
            res.add(new ArrayList<>(temp));     // we need to have a new ArrayList
            return;
        }
        for(int i = pos; i < candidates.length; i++) {
            if(candidates[i] > remain)  break;  // early detect, save much time
            temp.add(candidates[i]);
            remain -= candidates[i];
            backtracking(res, temp, i, candidates, remain);     // note the next pos
            remain += candidates[i];
            temp.remove(temp.get(temp.size() - 1));
        }
    }
}