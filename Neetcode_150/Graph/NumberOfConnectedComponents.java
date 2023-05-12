class Solution {
    public int findParent(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }
        return findParent(parent, parent[node]);
    }
    public int unionFindSolution(int n, int[][] edges) {
        int result = n;
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            int pA = findParent(parent, a);
            int pB = findParent(parent, b);
            if (pA != pB) {
                parent[pB] = pA;
                result -= 1;
            }
        }
        return result;
    }

    public int countComponents(int n, int[][] edges) {
        /**
        First thought:
        apply union find to group them together
        - find parent: O(log n)
        - union: O(1)

        do that for all edge: O(n log n)
        this is find for the problem

        what we need:
        - initially, we have n different components. Assume that no nodes connect to each other yet.
        - an array storing the parent of the nodes
            initial: p[i] = i
        - a function to get the parent of a node f(int[] parent, int node) -> int 
        - go through each edge: [a, b]
            parent a = pA
            parent b = pB
            if they are different: union and reduce the component count by 1
        - at the end we will have the number of different components 


        How to do it without union find?
        */

        return unionFindSolution(n, edges);
    }
}
