//Given a linked list, reverse the nodes of a linked list k at a time and return its modified list. 
//
// k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is. 
//
// 
// 
//
// Example: 
//
// Given this linked list: 1->2->3->4->5 
//
// For k = 2, you should return: 2->1->4->3->5 
//
// For k = 3, you should return: 3->2->1->4->5 
//
// Note: 
//
// 
// Only constant extra memory is allowed. 
// You may not alter the values in the list's nodes, only nodes itself may be changed. 
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

// reference: [206] reverse linked list
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)   return head;
        ListNode newHead = null;
        ListNode p = head;
        while(p != null)
        {
            ListNode temp = p.next;
            p.next = newHead;
            newHead = p;
            p = temp;
        }
        return newHead;
    }
}


// Iterative
// k = 3
// 0 1 2 3 4 5
// b f     e
// ->
// 0 3 2 1 4 5
//       b f    ...

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1)  return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode begin = dummy;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverseNode(begin, head.next);
                head = begin.next;
            }
            else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    // reference: [206] reverse linked list
    private ListNode reverseNode(ListNode begin, ListNode end) {
        if(begin == null || begin.next == null) return begin;
        ListNode first = begin.next;
        ListNode p = first, newHead = null;
        while(p != end) {
            ListNode temp = p.next;
            p.next = newHead;
            newHead = p;
            p = temp;
        }
        begin.next = newHead;
        first.next = p;
        return first;
    }
}


// Recursive
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null)    return head;
        ListNode p = head;
        for(int i = 0; i < k-1; i++)
        {
            p = p.next;
            if(p == null)   return head;
        }
        p = reverseKGroup(p.next, k);
        ListNode s;
        while(k-- > 0)
        {
            s = head;
            head = head.next;
            s.next = p;
            p = s;
        }
        return p;
    }
}