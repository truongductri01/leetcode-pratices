Source: https://leetcode.com/problems/copy-list-with-random-pointer/

Go through the list twice

- Create a parellel list by keepin track of another node as a representative node of the second list

- The first traversal will help to establish the list and create the copy version of the nodes

- The second traversal will use the map to assign to the random variable, make sure the random point to a valid node (not null)
