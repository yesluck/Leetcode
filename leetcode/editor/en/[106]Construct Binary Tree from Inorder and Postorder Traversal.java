//Given inorder and postorder traversal of a tree, construct the binary tree. 
//
// Note: 
//You may assume that duplicates do not exist in the tree. 
//
// For example, given 
//
// 
//inorder = [9,3,15,20,7]
//postorder = [9,15,7,20,3] 
//
// Return the following binary tree: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
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
    int post_idx;
    int[] inorder, postorder;
    Map<Integer, Integer> hm_inorder_idx = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;

        int n = postorder.length;
        post_idx = n - 1;

        int idx = 0;
        for (int val:inorder)
            hm_inorder_idx.put(val, idx++);
        return helper(0, n);
    }

    public TreeNode helper(int in_left, int in_right) {
        if (in_left == in_right)
            return null;

        int val = postorder[post_idx--];
        TreeNode root = new TreeNode(val);
        int in_idx = hm_inorder_idx.get(val);
        root.right = helper(in_idx + 1, in_right);
        root.left = helper(in_left, in_idx);

        return root;
    }
}