//Given a binary tree, determine if it is a valid binary search tree (BST). 
//
// Assume a BST is defined as follows: 
//
// 
// The left subtree of a node contains only nodes with keys less than the node's key. 
// The right subtree of a node contains only nodes with keys greater than the node's key. 
// Both the left and right subtrees must also be binary search trees. 
// 
//
// 
//
// Example 1: 
//
// 
//    2
//   / \
//  1   3
//
//Input: [2,1,3]
//Output: true
// 
//
// Example 2: 
//
// 
//    5
//   / \
//  1   4
//     / \
//    3   6
//
//Input: [5,1,4,null,null,3,6]
//Output: false
//Explanation: The root node's value is 5 but its right child's value is 4.
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


// inorder traversal
class Solution {
    public boolean isValidBST(TreeNode root) {
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        double prevNum = -Double.MAX_VALUE; // otherwise can't pass -2147483648 case

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (curr.val <= prevNum)
                    return false;
                prevNum = curr.val;
                curr = curr.right;
            }
        }
        return true;
    }
}


// recursion
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null)
            return true;

        int val = node.val;
        if (lower != null && val <= lower)
            return false;
        if (upper != null && val >= upper)
            return false;

        if (!helper(node.right, val, upper))
            return false;
        if (!helper(node.left, lower, val))
            return false;

        return true;
    }
}


// iterative
class Solution {
    Stack<TreeNode> stack = new Stack<>();
    Stack<Integer> lowers = new Stack<>(), uppers = new Stack<>();

    public boolean isValidBST(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.pop();
            lower = lowers.pop();
            upper = uppers.pop();

            if (root == null)
                continue;
            val = root.val;
            if (lower != null && val <= lower)
                return false;
            if (upper != null && val >= upper)
                return false;

            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    public void update (TreeNode root, Integer lower, Integer upper) {
        stack.push(root);
        lowers.push(lower);
        uppers.push(upper);
    }
}