//Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target. 
//
// Each number in candidates may only be used once in the combination. 
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
//Input: candidates = [10,1,2,7,6,1,5], target = 8,
//A solution set is:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,5,2,1,2], target = 5,
//A solution set is:
//[
//  [1,2,2],
//  [5]
//]
// 
//

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            if(i > pos && candidates[i] == candidates[i-1])  continue;  // core difference of avoiding duplicate
            temp.add(candidates[i]);
            remain -= candidates[i];
            backtracking(res, temp, i+1, candidates, remain);           // note the next pos
            remain += candidates[i];
            temp.remove(temp.get(temp.size() - 1));
        }
    }
}