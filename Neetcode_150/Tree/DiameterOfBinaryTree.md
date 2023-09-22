Problem: [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/description/)

<strong>First thought:</strong>
In order to solve this, we need an important observation: the diameter is the sum of the longest path on the left and right side of any subtree inside the main tree

![image](https://github.com/truongductri01/leetcode-pratices/assets/58579187/3b51d49e-aed1-4fb4-a63a-30d0b4f24029)
> This tree has a diameter of 6 which is the path [7, 5, 2, 1, 3, 6, 9] or [7, 5, 2, 1, 3, 6, 8]. With [7, 5, 2, 1] is the longest path on the left side of node `1` and [1, 3, 6, 9] (or [1, 3, 6, 8]) is the longest path on the right side of node `1`.

![image](https://github.com/truongductri01/leetcode-pratices/assets/58579187/7b0334ea-543d-4a2b-b79b-be63233d8ba8)
> Using the same observation, this tree has a diameter of 5 which is the path [7, 5, 3, 2, 4, 6]. With [7, 5, 2] is the longest path on the left side of node `2` and [2, 4, 6] is the longest path on the right side of node `2`. This path does not go through the `root` node since we are not considering the tree with that root as subtree for counting.



<strong>Implementation:</strong>
We do not need to keep track of the path of every subtree, what we need to keep is the longest path on the left and right side of every node (every subtree in other words). How to do so?

You can spend some time thinking about it and usually, you will find it very complicated to keep track of those numbers without creating a new tree with additional field for each node storing `leftLenght` and `rightLength`.

However, we can utilize DFS and recursion to solve this issue. With this approach, we just need to be aware of what we could return after each recursion. 
We will return an array of 2 integers. The first element represents the length of the longest path on the left of the node, and the second element represents the length of the longest path on the right of the node.

Here is a simple pseudocode:

```java
start with a node:
int leftRight = new int[2];
- if node has no left and right: 
    pass - nothing to update
- else if:
    if node has left:
        update left side length
        leftRight[0] = max(try with node.left)
    
    if node has right:
        update right side length
        leftRightArray[1] = max(try with node.right)
- maxValue = max of maxValue and sum of leftRight[0] + leftRight[1];
return leftRight
```

With this, we just need to keep track of a global variable `maxValue` to store the final result.


<strong>Solution:</strong>

```java

class Solution {
    int maxValue = 0;

    public int[] recursion(TreeNode node) {
        // System.out.println("At node: " + node.val);
        int[] leftRight = new int[2];
        if (node.left == null && node.right == null) {
            // nothing happen
        } else {
            if (node.left != null) {
                int[] leftSide = this.recursion(node.left);
                leftRight[0] = Math.max(leftSide[0], leftSide[1]) + 1;
            }

            if (node.right != null) {
                int[] rightSide = this.recursion(node.right);
                leftRight[1] = Math.max(rightSide[0], rightSide[1]) + 1;
            }
        }

        this.maxValue = Math.max(maxValue, leftRight[0] + leftRight[1]);

        return leftRight;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        recursion(root);
        return maxValue;
    }
}
```
