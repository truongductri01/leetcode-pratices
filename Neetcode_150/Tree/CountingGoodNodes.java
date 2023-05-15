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
    /**
    This is binary tree => left and right subtree

    First thought: we can maintain a max value so far for each branch
    - if the current node is larger than max
        increase the count
        update the max
    - this could be done recursively
    => at most traverse through all nodes in the tree

    O(V)
    the stack functino also cover all the nodes; O(V)
     */
    int count;
    
    public void traverse(TreeNode root, int maxValue) {
        if (root == null) {
            return;
        } else {
            if (root.val >= maxValue) {
                this.count ++;
            }

            traverse(root.left, Math.max(root.val, maxValue));
            traverse(root.right, Math.max(root.val, maxValue));
        }
    }

    public int goodNodes(TreeNode root) {
        this.count = 0;
        traverse(root, Integer.MIN_VALUE);
        return this.count;
    }
}
