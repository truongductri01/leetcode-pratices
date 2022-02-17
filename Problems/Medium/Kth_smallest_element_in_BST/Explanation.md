Problem Link: [Kth smallest element in Binary Search Tree](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)

### Requirements:

- given `root` of a BST, return the `kth` smallest element

### First thought:

- in BST, the node to the farthest left will be the smallest
- the parent node is smaller than any node on the right branch

### Idea:

- the idea is to start from the smallest node, and traverse back until we reach the kth smallest node
- use a pointer called `current` to keep track of the current TreeNode and return its when a condition below is met
- apply DFS:
  - keep track of a stack to store nodes for DFS purpose
  - keep track of a count variable starting at 0. This will store the order (smallest order) of the current node.
  - if there is a valid node which is not visited on the left of the current node, go to that one and put the current node to the stack
  - if there is no valid left node
    - increase the count variable, meaning that the current node is at `count` smallest position
    - if `count == k` return the current node
    - else if there is a valid right node and it has not been visited, go to that node
    - else, pop a node out from the stack and repeat the process
- when we finish such process, current node will stop at the `kth` smallest position

**Leetcode Solution is simpler - should learn from that**
