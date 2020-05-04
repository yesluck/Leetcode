//Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. 
//
// Return the linked list sorted as well. 
//
// Example 1: 
//
// 
//Input: 1->2->3->3->4->4->5
//Output: 1->2->5
// 
//
// Example 2: 
//
// 
//Input: 1->1->1->2->3
//Output: 2->3
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy, cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val)
                cur = cur.next;
            if (pre.next == cur)
                pre = pre.next;
            else
                pre.next = cur.next;
            cur = pre.next;
        }

        return dummy.next;
    }
}