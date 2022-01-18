/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /*
        use dummyHead as a new list
        - while loop until either one reach the end
        - then while loop to finish the other
        */
        ListNode dummyHead = new ListNode(-1);
        ListNode curNode = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curNode.next = list1;
                list1 = list1.next;
            } else {
                curNode.next = list2;
                list2 = list2.next;
            }
            curNode = curNode.next;
        }
        while (list1 != null) {
            curNode.next = list1;
            list1 = list1.next;
            curNode = curNode.next;
        }
        while (list2 != null) {
            curNode.next = list2;
            list2 = list2.next;
            curNode = curNode.next;
        }
        return dummyHead.next;
    }
}