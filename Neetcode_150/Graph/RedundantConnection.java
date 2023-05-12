class Solution {
    public int getParent(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }

        return getParent(parent, parent[node]);
    }
    public int[] findRedundantConnection(int[][] edges) {
        /**
        First thought: we need to detect the cycle in the graph, especially knows where thinks start
        Then we can just pick an edge in that cycle

        Or, we can: set the parent for the node
        so if the adding edges contain 2 node: a, b. If a and b has the same parent, return that edge as to be removed
        => Union find
         */
        int n = edges.length;
        int[] result = {0, 0};
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge: edges) {
            int p1 = getParent(parent, edge[0]);
            int p2 = getParent(parent, edge[1]);

            if (p1 == p2) {
                // the edges already exist in the tree
                result = edge;
                break;
            }

            parent[p2] = p1;
        }

        return result;
    }
}
