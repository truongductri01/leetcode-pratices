/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        int remainer = 0;
        
        while (l1 != null && l2 != null) {
            int total = l1.val + l2.val + remainer;
            if (total >= 10) {
                remainer = 1;
            } else {
                remainer = 0;
            }
            ListNode newNode = new ListNode(total % 10);
            
            cur.next = newNode;
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while (l1 != null) {
            int total = l1.val + remainer;
            if (total >= 10) {
                remainer = 1;
            } else {
                remainer = 0;
            }
            ListNode newNode = new ListNode(total % 10);
            cur.next = newNode;
            cur = cur.next;
            l1 = l1.next;
        }
        
        while (l2 != null) {
            int total = l2.val + remainer;
            if (total >= 10) {
                remainer = 1;
            } else {
                remainer = 0;
            }
            ListNode newNode = new ListNode(total % 10);
            cur.next = newNode;
            cur = cur.next;
            l2 = l2.next;
        }
        
        if (remainer == 1) {
            ListNode newNode = new ListNode(1);
            cur.next = newNode;
        }
        
        return dummy.next;
    }
}