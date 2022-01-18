import java.util.HashMap;
import java.util.Map;

/*
// Definition for a Node.
*/
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
       
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node curNode = head;
        Node dummyHead = new Node(-1);
        Node copiedHead = dummyHead;
        
        while (curNode != null) {
            Node newNode = new Node(curNode.val);
            copiedHead.next = newNode;
            
            map.put(curNode, newNode);
            
            copiedHead = copiedHead.next;
            curNode = curNode.next;
        }
        
        curNode = head;
        copiedHead = dummyHead.next;
        while (curNode != null) {
            if (curNode.random != null) {
                copiedHead.random = map.get(curNode.random);    
            }
            copiedHead = copiedHead.next;
            curNode = curNode.next;
        }
        
        
        return dummyHead.next;
    }
}