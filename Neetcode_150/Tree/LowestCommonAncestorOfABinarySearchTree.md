Problem: [here](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/)

### Solution:

``` java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /**
        
        since this is BST:
        1. if root.val == p.val or root.val == q.val: return root.
        2. if p.val < root.val && q.val < root.val:
            return lowestCommonAncestor(root.left, p, q);
        3. if p.val > root.val && q.val > root.val:
            return lowestCommonAncestor(root.right, p, q);
        4. else: return root;


        Runtime: average: O(log(n)) => at most run by half of the tree each iteration
        The worst case can be O(n) if each node just one child
         */

        if (root.val == p.val || root.val == q.val) {
            return root;
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
```
