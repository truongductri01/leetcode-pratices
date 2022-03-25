Problem link: [here](https://leetcode.com/problems/add-two-numbers/)

My solution: using dummyHead for LinkedList

- use dummyHead to easily keep track of new head of the list
- as we are adding numbers, remainder for addition should be kept (`1 + 9` will make a node of `0` and remainder of `1`)
- at the end of traversing both linked lists, if remainder is still `1`, create a new node
- return the node next to dummy head
