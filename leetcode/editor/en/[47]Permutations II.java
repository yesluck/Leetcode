//Given a collection of numbers that might contain duplicates, return all possible unique permutations. 
//
// Example: 
//
// 
//Input: [1,1,2]
//Output:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
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
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1])
                continue;
            temp.add(nums[i]);
            used[i] = true;
            backtracking(res, temp, used, nums);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}