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
    public int getListLength(ListNode head) {
        ListNode current = head;
        int result = 0;
        while (current != null) {
            result ++;
            current = current.next;
        }
        return result;
    }

    public ListNode recursion(ListNode head, int k, int counter) {
        if (counter == 0) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode nextHead = new ListNode(-1);
        // need to determine the next head first. 
        // do this while reversing is possible
        ListNode tail = head;
        ListNode current = head;
        for (int i = 0; i < k; i++) {
            if (i == k - 1) {
                // we have reached the tail and can determine the next head. 
                nextHead = current.next;
            }
            // in general, reverse the list;
            ListNode next = current.next;
            current.next = dummyHead.next;
            dummyHead.next = current;
            current = next; 
        }

        // call reverse for next head;
        ListNode newNextHead = recursion(nextHead, k, counter - 1);
        
        // linked with the tail
        tail.next = newNextHead;

        return dummyHead.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        /**
        We need to reverse k nodes at a time. We know the process of reversing a linked list, so this should be good

        What we needs is how to maintain the order of the nodes.

        Notes: we do not reverse if there are not enough nodes at the end of the list.  


        First thought:
        - We can store the nodes into an arraylist,
        - Simply reverse the position in the arraylist, then run through them and return as awhole linked list. 

        Runtime: O(n)
        Space: O(n)


        Second approach:
        - in place reverse using dummy head 
        - 
        */
        if (k == 1) {
            return head;
        }

        // 1. count the length of the list
        int listLength = getListLength(head);

        // 2. calculate the counter 
        int counter = listLength / k;

        // 3. start the recursion
        return recursion(head, k, counter);
    }
}
