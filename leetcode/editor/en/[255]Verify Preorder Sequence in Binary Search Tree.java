//Given an array of unique integers preorder, return true if it is the correct p
//reorder traversal sequence of a binary search tree. 
//
// 
// Example 1: 
//
// 
//Input: preorder = [5,2,1,3,6]
//Output: true
// 
//
// Example 2: 
//
// 
//Input: preorder = [5,2,6,1,3]
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= preorder.length <= 104 
// 1 <= preorder[i] <= 104 
// All the elements of preorder are unique. 
// 
//
// 
// Follow up: Could you do it using only constant space complexity? 
// Related Topics Stack Tree


//leetcode submit region begin(Prohibit modification and deletion)

// Recursive solution
class Solution {
    int i = 1;

    public boolean verifyPreorder(int[] preorder) {
        return preorder.length == 0 || (helper(preorder, Integer.MIN_VALUE, preorder[0]) && helper(preorder, preorder[0], Integer.MAX_VALUE));
    }

    public boolean helper(int[] preorder, int lowerBound, int upperBound) {
        // preorder[i] > upperBound 说明已经到本子树的叶子节点，需要往右子树扩张了。
        if (i == preorder.length || preorder[i] > upperBound)
            return true;
        int rootVal = preorder[i++];
        return rootVal > lowerBound && helper(preorder, lowerBound, rootVal) && helper(preorder, rootVal, upperBound);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
