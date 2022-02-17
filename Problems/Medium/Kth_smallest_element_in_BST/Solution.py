# Definition for a binary tree node.
from typing import Optional
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        visited = set()
        stack = []
        count = 0
        current = root
        
        while count < k:
            print(len(stack))
            if current.left and current.left not in visited:
                stack.append(current)
                current = current.left
            else:
                count += 1
                visited.add(current)
                if count == k:
                    return current.val
                
                if current.right and current.right not in visited:
                    current = current.right
                else:
                    current = stack.pop()
            
            # print(count, current.val, "-----------------")

        return current.val