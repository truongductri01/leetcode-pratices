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
    Map<Integer, Integer> preorderMap;
    Map<Integer, Integer> inorderMap;
    int[] preorder;
    int[] inorder;
    int length;
    public void recursion(TreeNode current, boolean isRoot, int leftBound, int rightBound) {
        int val = current.val;
        int preorderIdx = preorderMap.get(val);
        int inorderIdx = inorderMap.get(val);

        int leftDist = inorderIdx - leftBound;
        int rightDist = rightBound - inorderIdx;

        // System.out.println("leftDist: " + leftDist + " rightDist: " + rightDist);
        
        if (leftDist > 0) {
            int startLeft = preorderIdx + 1;
            int leftVal = preorder[startLeft];
            TreeNode left = new TreeNode(leftVal);
            current.left = left;
            // System.out.println("Left: " + left.val);
            recursion(left, false, leftBound, inorderIdx - 1);
        }
        if (rightDist > 0) {
            int startRight = preorderIdx + leftDist + 1;
            int rightVal = preorder[startRight];
            TreeNode right = new TreeNode(rightVal);
            current.right = right;
            // System.out.println("Right: " + right.val);
            recursion(right, false, inorderIdx + 1, rightBound);
        }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        preorderMap = new HashMap<>();
        inorderMap = new HashMap<>();
        length = preorder.length;
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < length; i++) {
            preorderMap.put(preorder[i], i);
            inorderMap.put(inorder[i], i);
        }
        
        recursion(root, true, 0, length - 1);

        return root;
    }
}

/**
- Need to use both to determine which node is the child of which
- We need to know the node at each order

Observation:
    - first node in preorder is the root
    - anything come after is its precendent
    - if in-order has element come before the root -> it is the left child
        otherwise, only right child?

Observation 2:
    - in pre-order: what comes first is the parent
    - in in-order: what comes first is the child

Observation 3:
    - what comes before a node in in-order is the left precesndant & what comes after is the right precesdant


How to tell which node is the imme. child of a node?
    - if there is anything before a node in in-order
        => the immediate value in pre-order is its left child and that apply for right side too

    That's mean, for each node, we just have to detect which one is before or after it?


Algo:
    - at each one, need to know the index of the parent - in in-order!
    - Assign TreeNode for each element in pre-order
    - for each one in preorder, number x with index of i=0 in pre-order and j=3 in in-order
        + if j > 0: 
            then the next i elements is the left subtree with starting node to be i + 1
            and the starting node of right subtree is (i + j) + 1 if j < length of in-order - 1
        + else:
            no left tree, i + 1 is the right subtree starting points

    - repeat for all elements in the tree and return 

recursion called: (TreeNode current, bool isRoot, int parentVal):
    int val = current.val;
    int preorderIdx = preorderMap.get(val)
    int inorderIdx = inOrderMap.get(val)
    int parentInorderIdx = inorderMap.get(parentVal)

    int leftDist = isRoot? inorderIdx: inOrderIx - (parentIdxInorder + 1);
    int rightDist = isRoot? length - 1 - preorderIdx: parentInorderIdx - inorderIdx - 1;
    
    if (leftDist > 0):
        int startLeft = preorderIdx + 1;
        int leftVal = preorder(startLeft);
        create left node
        call on left node
    if (rightDist > 0):
        int startRight = preorderIdx + leftDist;
        int rightVal = preorder(startRight);
        create right node
        call on right node




3
 \
  9
 / \
4   5 


   3
  /   \
 9.    8
/ \.  / \
4  5. 11  10


 */
```
