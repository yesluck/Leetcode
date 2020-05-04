//Given a binary tree, flatten it to a linked list in-place. 
//
// For example, given the following tree: 
//
// 
//    1
//   / \
//  2   5
// / \   \
//3   4   6
// 
//
// The flattened tree should look like: 
//
// 
//1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
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
    public void flatten(TreeNode root) {
        flattenTree(root);
    }

    public TreeNode flattenTree(TreeNode node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null)
            return node;

        TreeNode leftTail = flattenTree(node.left);
        TreeNode rightTail = flattenTree(node.right);

        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail == null ? leftTail : rightTail;
    }
}


// iterative ~ Morris
// [Recursion is all about postponing decisions until something else is completed.]
class Solution {
    public void flatten(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root, pre;
        while (curr != null) {
            if (curr.left != null) {
                pre = curr.left;    // pre: 进入curr的左子树
                while (pre.right != null)   // pre: 在左子树中寻找最右节点
                    pre = pre.right;
                pre.right = curr.right;   // 相连：将最右节点与curr.right相连
                TreeNode temp = curr;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
