//Two elements of a binary search tree (BST) are swapped by mistake. 
//
// Recover the tree without changing its structure. 
//
// Example 1: 
//
// 
//Input: [1,3,null,null,2]
//
//   1
//  /
// 3
//  \
//   2
//
//Output: [3,1,null,null,2]
//
//   3
//  /
// 1
//  \
//   2
// 
//
// Example 2: 
//
// 
//Input: [3,1,4,null,null,2]
//
//  3
// / \
//1   4
//   /
//  2
//
//Output: [2,1,4,null,null,3]
//
//  2
// / \
//1   4
//   /
//  3
// 
//
// Follow up: 
//
// 
// A solution using O(n) space is pretty straight forward. 
// Could you devise a constant space solution? 
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


// recursive inorder traversal + find swapped traversal + recover traversal
class Solution {
    List<Integer> res = new ArrayList<>();
    int[] swapped;

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
        return res;
    }

    public void findSwappedPairs() {
        int n = res.size();
        int x = -1, y = -1;
        for (int i = 1; i < n; i++) {
            if (res.get(i) < res.get(i - 1)) {
                y = res.get(i);
                if (x == -1)
                    x = res.get(i - 1);
                else
                    break;
            }
        }
        swapped = new int[]{x, y};
    }

    public void recover(TreeNode r, int count) {
        int x = swapped[0], y = swapped[1];
        if (r != null) {
            if (r.val == x || r.val == y) {
                r.val = r.val == x ? y : x;
                if (--count == 0)
                    return;
            }
            recover(r.left, count);
            recover(r.right, count);
        }
    }

    public void recoverTree(TreeNode root) {
        inorderTraversal(root);
        findSwappedPairs();
        recover(root, 2);
    }
}


// [iterative inorder + find swapped pairs] traversal + directly swap
class Solution {
    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root, prev = null;
        TreeNode x = null, y = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (prev != null && curr.val < prev.val) {
                    y = curr;
                    if (x == null)
                        x = prev;
                    else
                        break;
                }
                prev = curr;
                curr = curr.right;
            }
        }

        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }
}


// [recursive inorder + find swapped pairs] traversal + directly swap
class Solution {
    public TreeNode curr = null, prev = null;
    public TreeNode x = null, y = null;

    public void recoverTree(TreeNode root) {
        findSwappedPairs(root);
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

    public void findSwappedPairs(TreeNode root) {
        if (root != null) {
            findSwappedPairs(root.left);
            if (prev != null && root.val < prev.val) {
                y = root;
                if (x == null)
                    x = prev;
                else
                    return;
            }
            prev = root;
            findSwappedPairs(root.right);
        }
    }
}


// Morris
class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode curr = root, pre = null, prev = null;
        TreeNode x = null, y = null;
        while (curr != null) {
            if (curr.left != null) {
                pre = curr.left;
                while (pre.right != null && pre.right != curr)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    if (prev != null && curr.val < prev.val) {
                        y = curr;
                        if (x == null)
                            x = prev;
                    }
                    prev = curr;
                    curr = curr.right;
                    pre.right = null;
                }
            } else {
                if (prev != null && curr.val < prev.val) {
                    y = curr;
                    if (x == null)
                        x = prev;
                }
                prev = curr;
                curr = curr.right;
            }
        }
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }
}