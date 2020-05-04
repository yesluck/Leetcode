//Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST. 
//
// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1. 
//
// Example: 
//
// 
//Given the sorted linked list: [-10,-3,0,5,9],
//
//One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
//

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        ListNode mid = findMiddleElement(head);
        TreeNode node = new TreeNode(mid.val);
        if (head == mid)
            return node;

        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    public ListNode findMiddleElement(ListNode head) {
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null)
            prev.next = null;
        return slow;
    }
}


// convert to array and ~108
// ...


// inorder simulation
class Solution {
    ListNode head;

    public TreeNode sortedListToBST(ListNode head) {
        int size = findSize(head);
        this.head = head;
        return convertListToBST(0, size - 1);
    }

    public int findSize(ListNode head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }

    public TreeNode convertListToBST(int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2;

        // ensure inorder traversal order, so that just needs to traverse LinkedList from left to right
        TreeNode leftNodes = convertListToBST(left, mid - 1);
        TreeNode node = new TreeNode(this.head.val);
        node.left = leftNodes;
        this.head = this.head.next;
        node.right = this.convertListToBST(mid + 1, right);
        return node;
    }
}