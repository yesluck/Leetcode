//Given a linked list, swap every two adjacent nodes and return its head. 
//
// You may not modify the values in the list's nodes, only nodes itself may be changed. 
//
// 
//
// Example: 
//
// 
//Given 1->2->3->4, you should return the list as 2->1->4->3.
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
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)   return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy, left = head, right = head.next;
        while(left != null && left.next != null) {
            right = left.next;
            // (1) must before (2), otherwise the "right.next" used in (1) will be changed by (2)
            left.next = right.next; // (1)
            right.next = left;      // (2)
            p.next = right;
            p = left;
            left = left.next;
        }
        return dummy.next;
    }
}