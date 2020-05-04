//Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level). 
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
//return its level order traversal as: 
// 
//[
//  [3],
//  [9,20],
//  [15,7]
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


// recursive
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        bfs(res, root, 0);
        return res;
    }

    public void bfs(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null)
            return;
        if (res.size() == level)
            res.add(new ArrayList<>());
        res.get(level).add(root.val);
        bfs(res, root.left, level + 1);
        bfs(res, root.right, level + 1);
    }
}


// iterative
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null)
            return res;

        q.add(root);
        while (!q.isEmpty()) {
            res.add(new ArrayList<>());
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                res.get(res.size() - 1).add(curr.val);
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
            }
        }
        return res;
    }
}