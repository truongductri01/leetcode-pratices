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
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.printf("%d ->", current.val);
            current = current.next;
        }
        System.out.println();
    }
    public ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = dummy.next;
            dummy.next = current;
            current = next;
        }
        return dummy.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        ListNode current1 = head1;
        ListNode current2 = head2;

        while (current1 != null && current2 != null) {
            current.next = current1;
            current1 = current1.next;
            current.next.next = current2;
            current2 = current2.next;
            current = current.next.next;
        }

        while (current1 != null) {
            current.next = current1;
            current1 = current1.next;
            current = current.next;
        }
        while (current2 != null) {
            current.next = current2;
            current2 = current2.next;
            current = current.next;
        }

        return dummy.next;
    }

    public void reorderList(ListNode head) {
        /**
        Thought:
        split the list in half
        then add like merging the list`

        then we need to reverse the second list?

        First approach:
        - split the list in half, A and B
        - reverse B
        - merge A and B together

        Runtime: O(n)
        Space: O(1) since no extra space needed

        Can we solve it without having to reverse the second half?

        Split it in half:
        - need to know the length of list first 
            if length is even 2k => middle is k.
            if length is odd: 2k + 1 => middle is also k. 

        - reverse linked list: use a dummy head for ease

        Now have 2 list: L0 -> ... -> Lx-1 and Ln -> ... -> Lx (after reverse)
        merge:
            p1 and p2
            next1 and next2
            with a dummy head also
            current = dummy
            while p1 and p2 not none:
                clear data first:
                    next1 = p1.next
                    next2 = p2.next
                p1.next = none
                p2.next = none
                current.next = p1
                current.next.next = p2
                current = current.next.next
                p1 = next1
                p2 = next2
            if p1 not none:
                current.next = p1
                p1.next = none
            
            return dummy.next
        */
        // printList(head);
        int n = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            n ++;
        }
        // System.out.println(n);
        int half = n /2;
        if (n % 2 == 1) half ++;
        ListNode halfNode;
        current = head;
        for (int i = 0; i < half; i++) {
            ListNode next = current.next;
            // remove the connection between the two halves
            if (i == half - 1) {
                current.next = null;
            }
            current = next;
        }
        halfNode = current;

        ListNode head1 = head;
        ListNode head2 = reverse(halfNode);
        // printList(head1);
        // printList(head2);
        ListNode newHead = merge(head1, head2);
        // printList(newHead);
    }
}


// Second solution
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
    public void reorderList(ListNode head) {
        /**
        We can reorder this by assigning the index for each node and combine them using that index
        */
        int n = 0;
        List<ListNode> original = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            original.add(current);
            n ++;
            current = current.next;
        }


        List<ListNode> modify = new ArrayList<>();
        int half = n / 2;
        if (n % 2 == 1) half ++;
        for (int i = 0; i < half; i++) {
            modify.add(original.get(i));
            if (i != n - 1 - i) {
                modify.add(original.get(n - 1 - i));
            }
        }

        for (int i = 0; i < modify.size(); i++) {
            if (i == modify.size() - 1) {
                modify.get(i).next = null;
            } else {
                modify.get(i).next = modify.get(i + 1);
            }
        }
    }
}
