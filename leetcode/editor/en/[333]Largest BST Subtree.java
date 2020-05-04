//Given a binary tree, find the largest subtree which is a Binary Search Tree (B
//ST), where largest means subtree with largest number of nodes in it. 
//
// Note: 
//A subtree must include all of its descendants. 
//
// Example: 
//
// 
//Input: [10,5,15,1,8,null,7]
//
//   10 
//   / \ 
//  5  15 
// / \   \ 
//1   8   7
//
//Output: 3
//Explanation: The Largest BST Subtree in this case is the highlighted one.
//             The return value is the subtree's size, which is 3.
// 
//
// Follow up: 
//Can you figure out ways to solve it with O(n) time complexity? 
// Related Topics Tree


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// recursion in a top-down manner
class Solution {
    public class Result {
        int size;
        int lower;
        int upper;

        Result(int size_, int lower_, int upper_) {
            size = size_;
            lower = lower_;
            upper = upper_;
        }
    }

    public int res;

    public int largestBSTSubtree(TreeNode root) {
        if (root == null)
            return 0;
        traverse(root);
        return res;
    }

    public Result traverse(TreeNode root) {
        if (root == null)
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        Result left = traverse(root.left), right = traverse(root.right);
        if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower)
            return new Result(-1, 0, 0);
        res = Math.max(res, 1 + left.size + right.size);
        return new Result(1 + left.size + right.size, Math.min(root.val, left.lower), Math.max(root.val, right.upper));
    }
}