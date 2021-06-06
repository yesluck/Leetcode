//Given the root of a binary tree, return the length of the longest consecutive 
//sequence path. 
//
// The path refers to any sequence of nodes from some starting node to any node 
//in the tree along the parent-child connections. The longest consecutive path nee
//ds to be from parent to child (cannot be the reverse). 
//
// 
// Example 1: 
//
// 
//Input: root = [1,null,3,2,4,null,null,null,5]
//Output: 3
//Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
// 
//
// Example 2: 
//
// 
//Input: root = [2,null,3,2,null,1]
//Output: 2
//Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
//
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 3 * 104]. 
// -3 * 104 <= Node.val <= 3 * 104 
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

// My Solution: (recursion)

class Solution {
    public int res = 0;

    public int longestConsecutive(TreeNode root) {
        helper(root);
        return res;
    }

    public int helper(TreeNode root) {
        if (root == null)
            return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        int temp = 1;
        if (root.left != null && root.left.val == root.val + 1)
            temp = left + 1;
        if (root.right != null && root.right.val == root.val + 1)
            temp = Math.max(temp, right + 1);
        res = Math.max(temp, res);
        return temp;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
