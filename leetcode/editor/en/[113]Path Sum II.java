//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given the below binary tree and sum = 22, 
//
// 
//      5
//     / \
//    4   8
//   /   / \
//  11  13  4
// /  \    / \
//7    2  5   1
// 
//
// Return: 
//
// 
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
//

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// backtracking (dfs)
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), root, sum);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> temp, TreeNode root, int sum) {
        if (root == null)
            return;
        if (root.left == null && root.right == null && sum == root.val) {
            temp.add(root.val);
            res.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        temp.add(root.val);
        sum -= root.val;
        helper(res, temp, root.left, sum);
        helper(res, temp, root.right, sum);
        sum += root.val;
        temp.remove(temp.size() - 1);
    }
}