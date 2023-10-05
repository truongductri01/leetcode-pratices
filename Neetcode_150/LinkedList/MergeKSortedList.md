Problem: [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/description/)

<strong>Solution</strong>

If you are able to solve the merge 2 sorted linked list, you will be able to do this

By spliting k lists into groups of a pair of linked lists, we can merge each pair, then repeat the process until there is only one big sorted list left to be returned.
Remember to handle edge case like when there is no linked list in the list at all then we should be good.

```java

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
    public ListNode merge2Lists(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(-1);
        ListNode dummyCurrent = dummy;
        ListNode current1 = node1;
        ListNode current2 = node2;

        while (current1 != null && current2 != null) {
            if (current1.val <= current2.val) {
                dummyCurrent.next = current1;
                current1 = current1.next;
            } else {
                dummyCurrent.next = current2;
                current2 = current2.next;
            }
            dummyCurrent = dummyCurrent.next;
        }

        while (current1 != null) {
            dummyCurrent.next = current1;
            current1 = current1.next;
            dummyCurrent = dummyCurrent.next;
        }

        while (current2 != null) {
            dummyCurrent.next = current2;
            current2 = current2.next;
            dummyCurrent = dummyCurrent.next;
        }

        return dummy.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        List<ListNode> initialList = new ArrayList<>();
        for (ListNode node: lists) {
            initialList.add(node);
        }

        while (initialList.size() > 1) {
            List<ListNode> newList = new ArrayList<>();
            int idx = 0;
            while (idx < initialList.size()) {
                if (idx == initialList.size() - 1) {
                    newList.add(initialList.get(idx));
                    idx ++;
                } else {
                    newList.add(merge2Lists(initialList.get(idx), initialList.get(idx + 1)));
                    idx += 2;
                }
            }
            initialList = newList;
        }

        return initialList.get(0);
    }
}
```
