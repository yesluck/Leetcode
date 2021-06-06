//Given the root of a binary tree, return the number of uni-value subtrees. 
//
// A uni-value subtree means all nodes of the subtree have the same value. 
//
// 
// Example 1: 
//
// 
//Input: root = [5,1,5,5,5,null,5]
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: root = [5,5,5,5,5,null,5]
//Output: 6
// 
//
// 
// Constraints: 
//
// 
// The numbrt of the node in the tree will be in the range [0, 1000]. 
// -1000 <= Node.val <= 1000 
// 
// Related Topics Tree


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// My answer (recursive)
class Solution {
    int res;

    public int countUnivalSubtrees(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }

    public boolean helper(TreeNode root) {
        if (root == null)
            return true;

        boolean leftUni = helper(root.left);
        boolean rightUni = helper(root.right);

        if (leftUni && rightUni) {
            if ((root.left == null || root.val == root.left.val) &&
                    (root.right == null || root.val == root.right.val)) {
                res += 1;
                return true;
            }
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
