//Given a binary tree containing digits from 0-9 only, each root-to-leaf path co
//uld represent a number. 
//
// An example is the root-to-leaf path 1->2->3 which represents the number 123. 
//
//
// Find the total sum of all root-to-leaf numbers. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// 
//Input: [1,2,3]
//    1
//   / \
//  2   3
//Output: 25
//Explanation:
//The root-to-leaf path 1->2 represents the number 12.
//The root-to-leaf path 1->3 represents the number 13.
//Therefore, sum = 12 + 13 = 25. 
//
// Example 2: 
//
// 
//Input: [4,9,0,5,1]
//    4
//   / \
//  9   0
// / \
//5   1
//Output: 1026
//Explanation:
//The root-to-leaf path 4->9->5 represents the number 495.
//The root-to-leaf path 4->9->1 represents the number 491.
//The root-to-leaf path 4->0 represents the number 40.
//Therefore, sum = 495 + 491 + 40 = 1026. 
// Related Topics Tree Depth-first Search


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


// Recursive: my answer
class Solution {
    int res;

    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;

        res = 0;
        helper(root, root.val);
        return res;
    }

    public void helper(TreeNode root, int temp) {
        if (root.left == null && root.right == null) {
            res += temp;
            return;
        }

        if (root.left != null)
            helper(root.left, temp * 10 + root.left.val);
        if (root.right != null)
            helper(root.right, temp * 10 + root.right.val);
    }
}


// Recursive: almost the same
class Solution {
    int res;

    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;

        res = 0;
        helper(root, 0);
        return res;
    }

    public void helper(TreeNode root, int temp) {
        if (root == null)
            return;

        temp = temp * 10 + root.val;

        if (root.left == null && root.right == null) {
            res += temp;
            return;
        }

        helper(root.left, temp);
        helper(root.right, temp);
    }
}


// Iterative
class Solution {
    public int sumNumbers(TreeNode root) {
        int res = 0;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        TreeNode curr = root;
        int currSum = 0;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                currSum = currSum * 10 + curr.val;
                stack.push(new Pair(curr, currSum));
                if (curr.left == null && curr.right == null)
                    res += currSum;
                curr = curr.left;
            } else {
                Pair<TreeNode, Integer> pop = stack.pop();
                curr = pop.getKey();
                currSum = pop.getValue();
                curr = curr.right;
            }
        }
        return res;
    }
}


// Morris
