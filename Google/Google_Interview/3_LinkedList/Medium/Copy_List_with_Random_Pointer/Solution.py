"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""

class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        nodeDict = dict()
        cur = head
        dummyHead = Node(-1)
        copied = dummyHead
        while cur:
            newNode = Node(cur.val)
            copied.next = newNode
            nodeDict[cur] = newNode
            
            copied = copied.next
            cur = cur.next
            
        cur = head
        copied = dummyHead.next
        while cur:
            if cur.random is not None:
                copied.random = nodeDict[cur.random]
                
            copied = copied.next
            cur = cur.next
            
        return dummyHead.next