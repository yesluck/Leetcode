//Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root). 
//
// 
//For example: 
//Given binary tree [3,9,20,null,null,15,7], 
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// 
// 
//return its bottom-up level order traversal as: 
// 
//[
//  [15,7],
//  [9,20],
//  [3]
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
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null)
            return res;

        q.add(root);
        while (!q.isEmpty()) {
            res.add(0, new ArrayList<>());
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                res.get(0).add(curr.val);
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
            }
        }
        return res;
    }
}