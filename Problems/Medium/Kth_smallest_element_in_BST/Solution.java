import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        /*
        Has to go through the array anyway
        But just store the smallest k, so when needed, return the last element
        This is a binary search tree => prioritize to go the the left branch first
        
        
        Use a stack (array) to store the element
        Keep track of the current node
        if go left, append current to stack
        */
        
        Set<TreeNode> visited = new HashSet<>();
        List<TreeNode> list = new ArrayList<>();
        TreeNode current = root;
        int count = 0;
        
        while (count < k) {
            if (current.left != null && !visited.contains(current.left)) {
                list.add(current);
                current = current.left;
            } else {
                count ++;
                visited.add(current);
                
                if (count == k) {
                    return current.val;
                }
                
                if (current.right != null && !visited.contains(current.right)) {
                    current = current.right;
                }
                else {
                    current = list.get(list.size() - 1);
                    list.remove(list.size() - 1);
                }
            }
        }
        
        return current.val;
    }
}