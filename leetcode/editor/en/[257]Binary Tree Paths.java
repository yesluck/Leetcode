//Given the root of a binary tree, return all root-to-leaf paths in any order. 
//
// A leaf is a node with no children. 
//
// 
// Example 1: 
//
// 
//Input: root = [1,2,3,null,5]
//Output: ["1->2->5","1->3"]
// 
//
// Example 2: 
//
// 
//Input: root = [1]
//Output: ["1"]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 100]. 
// -100 <= Node.val <= 100 
// 
// Related Topics Tree Depth-first Search


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

// Recursive
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        helper(root, "", paths);
        return paths;
    }

    public void helper(TreeNode root, String path, List<String> paths) {
        if (root == null)
            return;

        path += Integer.toString(root.val);
        if (root.left == null && root.right == null) {
            paths.add(path);
        } else {
            path += "->";
            helper(root.left, path, paths);
            helper(root.right, path, paths);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
