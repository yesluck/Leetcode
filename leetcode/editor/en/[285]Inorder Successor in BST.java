//Given the root of a binary search tree and a node p in it, return the in-order
// successor of that node in the BST. If the given node has no in-order successor 
//in the tree, return null. 
//
// The successor of a node p is the node with the smallest key greater than p.va
//l. 
//
// 
// Example 1: 
//
// 
//Input: root = [2,1,3], p = 1
//Output: 2
//Explanation: 1's in-order successor node is 2. Note that both p and the return
// value is of TreeNode type.
// 
//
// Example 2: 
//
// 
//Input: root = [5,3,6,2,4,null,null,1], p = 6
//Output: null
//Explanation: There is no in-order successor of the current node, so the answer
// is null.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 104]. 
// -105 <= Node.val <= 105 
// All Nodes will have unique values. 
// 
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
// My answer (recursive)
class Solution {
    TreeNode res;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        res = null;
        helper(root, p);
        return res;
    }

    public void helper(TreeNode root, TreeNode p) {
        if (root == null)
            return;

        if (root.val > p.val) {
            res = root;
            helper(root.left, p);
        } else {
            helper(root.right, p);
        }
    }
}

// Solution (equvalent iterative):
class Solution {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        TreeNode successor = null;

        while (root != null) {

            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
