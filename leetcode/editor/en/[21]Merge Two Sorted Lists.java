//Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists. 
//
// Example:
// 
//Input: 1->2->4, 1->3->4
//Output: 1->1->2->3->4->4
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

// iterative1
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        ListNode p1 = l1, p2 = l2;
        while(p1 != null && p2 != null) {
            if(p1.val < p2.val) {
                res.next = new ListNode(p1.val);
                p1 = p1.next;
            }
            else {
                res.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            res = res.next;
        }
        while(p1 != null) {
            res.next = new ListNode(p1.val);
            p1 = p1.next;
            res = res.next;
        }
        while(p2 != null) {
            res.next = new ListNode(p2.val);
            p2 = p2.next;
            res = res.next;
        }
        return dummy.next;
    }
}

// iterative2, not renewing new ListNodes
class Solution{
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                p = p.next;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = p.next;
                l2 = l2.next;
            }
        }
        if(l1==null){
            p.next=l2;
        }
        if(l2==null){
            p.next=l1;
        }
        return dummy.next;
    }
}

// recursive
class Solution2 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)  return l2;
        if(l2 == null)  return l1;
        if(l1.val < l2.val)
        {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else
        {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}