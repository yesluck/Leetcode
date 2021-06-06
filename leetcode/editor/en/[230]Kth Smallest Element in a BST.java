//Given the root of a binary search tree, and an integer k, return the kth (1-in
//dexed) smallest element in the tree. 
//
// 
// Example 1: 
//
// 
//Input: root = [3,1,4,null,2], k = 1
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: root = [5,3,6,2,4,null,null,1], k = 3
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is n. 
// 1 <= k <= n <= 104 
// 0 <= Node.val <= 104 
// 
//
// 
//Follow up: If the BST is modified often (i.e., we can do insert and delete ope
//rations) and you need to find the kth smallest frequently, how would you optimiz
//e? Related Topics Binary Search Tree


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

// My solution ~173
class Solution {
    Stack<TreeNode> stack;

    private void leftMostNodes(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        stack = new Stack<>();

        leftMostNodes(root);

        while (k > 0) {
            k--;
            TreeNode topNodeInStack = stack.pop();
            if (k == 0) {
                return topNodeInStack.val;
            }

            if (topNodeInStack.right != null) {
                leftMostNodes(topNodeInStack.right);
            }
        }

        return -1;
    }
}


// Iterative solution (same but clear)
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
