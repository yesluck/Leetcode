//Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity. 
//
// Example: 
//
// 
//Input:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//Output: 1->1->2->3->4->4->5->6
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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)   return null;
        int interval = 1;
        while(interval < lists.length) {
            for(int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = merge2Lists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }
    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        if(l1 == null)  return l2;
        else if(l2 == null)  return l1;
        if(l1.val < l2.val) {
            l1.next = merge2Lists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = merge2Lists(l1, l2.next);
            return l2;
        }
    }
}