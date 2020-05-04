//Given a binary tree, return the inorder traversal of its nodes' values. 
//
// Example: 
//
// 
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,3,2] 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
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
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
        return res;
    }
}


// iterative
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }
}


// Morris
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root, pre;
        while (curr != null) {
            if (curr.left != null) {
                pre = curr.left;    // pre: 进入curr的左子树
                while (pre.right != null)   // pre: 在左子树中寻找最右节点
                    pre = pre.right;
                pre.right = curr;   // 相连：将最右节点与curr相连
                TreeNode temp = curr;
                curr = curr.left;
                temp.left = null;   // 删除：将curr的左连接断开（防止成环）
            } else {
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }
}