//Given two integers n and k, return all possible combinations of k numbers out of 1 ... n. 
//
// Example: 
//
// 
//Input: n = 4, k = 2
//Output:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// 
//

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    public void backtracking(List<List<Integer>> res, List<Integer> temp, int n, int k, int pos) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = pos; i < n + 1; i++) {
            temp.add(i);
            backtracking(res, temp, n, k, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}