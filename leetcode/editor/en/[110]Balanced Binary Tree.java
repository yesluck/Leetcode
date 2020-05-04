//Given a binary tree, determine if it is height-balanced. 
//
// For this problem, a height-balanced binary tree is defined as: 
//
// 
// a binary tree in which the left and right subtrees of every node differ in height by no more than 1. 
// 
//
// 
//
// Example 1: 
//
// Given the following tree [3,9,20,null,null,15,7]: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// Return true. 
// 
//Example 2: 
//
// Given the following tree [1,2,2,3,3,null,null,4,4]: 
//
// 
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 
//
// Return false. 
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

// Top-down recursion (intuitive!) O(nlogn)
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}


// Bottom-up recursion O(n)
class TreeInfo {
    public final int height;
    public final boolean balanced;

    public TreeInfo(int height, boolean balanced) {
        this.height = height;
        this.balanced = balanced;
    }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        return helper(root).balanced;
    }

    public TreeInfo helper(TreeNode root) {
        if (root == null)
            return new TreeInfo(0, true);

        TreeInfo left = helper(root.left);
        if (!left.balanced)
            return new TreeInfo(-1, false);
        TreeInfo right = helper(root.right);
        if (!right.balanced)
            return new TreeInfo(-1, false);

        if (Math.abs(left.height - right.height) <= 1)
            return new TreeInfo(Math.max(left.height, right.height) + 1, true);
        return new TreeInfo(-1, false);
    }
}