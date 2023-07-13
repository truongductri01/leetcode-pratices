<strong>814. Binary Tree Pruning</strong>: [here](https://leetcode.com/problems/binary-tree-pruning/description/)


### 1. Understand the problem

In this situation, we need to be able to remove a subtree that does not contain any node with a value of 1.

So if we have a root = [0] => we will return null since the whole tree will get removed.

Also, we have another observation is that, if we have a TreeNode `node` and the left subtree: `node.left` and right subtree `node.right`, then `node` as a tree will contains 1 if either `node.val == 1 or node.left contains 1 or node.right contains 1`

So, we can utilize this observation to implement a DFS approach for this problem.

### 2. Approaches:

As we can see, when we can determine whether the left and right subtree of a node contains any node with a value of 1 or not, we will be able to 1) remove that subtree and 2) know if the current node does contain a node of 1 or not.

### 3. Solution:

```java
/** 
      helper function called hasOne(TreeNode node):
      - Base case: if no left and no right sub tree
          - if root.val == 0:
              return false
          - else:
              return true
      
      boolean hasOne = false;
      - if left:
          + left result = hasOne(node.left)
          + if ! left result:
              node.left = null
          else:
              hasOne = true;
      - if right
          + right result = hasOne(node.right)
          + ! right result:
              node.right = null
          + else:
              hasOne = true
      
      return hasOne
*/
```

### 4. Implementation with Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasOne(TreeNode root) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == 1;
        }

        boolean doesHaveOne = root.val == 1;

        if (root.left != null) {
            boolean leftResult = hasOne(root.left);
            if (leftResult) {
                doesHaveOne = true;
            } else {
                root.left = null;
            }
        }

        if (root.right != null) {
            boolean rightResult = hasOne(root.right);
            if (rightResult) {
                doesHaveOne = true;
            } else {
                root.right = null;
            }
        }

        return doesHaveOne;
    }

    public TreeNode pruneTree(TreeNode root) {
        boolean rootHasOne = hasOne(root);
        if (rootHasOne) {
            return root;
        }
        return null;
    }
}

```
