Problem: https://leetcode.com/problems/find-leaves-of-binary-tree/

## Requirements:

- Input: root of a binary tree
- Output: list of leaves but level
- Process:
  - Collect all the leaf nodes
  - Remove all the leaf nodes
  - Repeat until the tree is empty

<img src="https://assets.leetcode.com/uploads/2021/03/16/remleaves-tree.jpg" alt="Demonstration">

## Idea

- We keep track of the level of each node
- We define level of a node as the maximum level of its left and right branch
- Lets say in the picture, the level of node `3, 4, 5` is `0`, level of `2` is `1` and level of `1` is `2`
- So in order to get the correct level of the node, we can use `DFS (depth-first-search)`
- We store the node in a dictionary with its corresponding level as the key

Example:

```python
levelDict = {
    0: [3, 4, 5],
    1: [2],
    2: [1]
}
```

- Then simply start at `currentLevel = 0` and append `levelDict[currentLevel]` to the result list. Then increase `currentLevel` by 1 until there is no level equal to the variable
