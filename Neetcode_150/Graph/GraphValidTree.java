class Solution {
    public boolean validTree(int n, int[][] edges) {
        /**
        make sure there is no cycle within the tree
        
        First thought:
        - establish the tree from the edges
        - traverse through the tree and check if there is any node that re-visit a visited node 
            how?
            cannot determine based on the visited set


        Question:
        - algorithm to detect a cycle  in a graph? 
            topological sort?
            Kahn's algo? using the in-degree and out-degree?
            If at any point, there is no node with a degree of 1, then there must be a cycle?

            Yes, this could be used to detect the cycle in the graph

        Algorithm:
        - Go through the edges in the tree, increase the degree of the node
        - keep track a map of adjacents nodes to a specific nodes 
        - find the node with the smallest degree, if that node does not have a degree <= 1, return false since there is a cycle 
            - else: for each adjacent node to that node, decrease the degree of that adjacent node by 1
        - repeat the process untill all node has a degree of 0 and are all visited
            - how to keep track of a set of node, store the nodes in set and see if which one can be considered
        
        Need: an array to store the degree, a map to store the adjacnet node list, a set storing which nodes are not visited yet
        */
        int[] degree = new int[n];
        Set<Integer> notVisited = new HashSet<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();

        if (edges.length < n - 1) {
            return false;
        }

        for (int[] edge: edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            degree[node1] ++;
            degree[node2]++;

            notVisited.add(node1);
            notVisited.add(node2);

            // add to the map 
            map.putIfAbsent(node1, new HashSet<>());
            map.putIfAbsent(node2, new HashSet<>());

            map.get(node1).add(node2);
            map.get(node2).add(node1);

            // union find and make sure they all have the same parents?
        }
        // System.out.println(Arrays.toString(degree));

        while (!notVisited.isEmpty()) {
            // find the node with the lowest degree 
            int nodeLowestDegree = Integer.MAX_VALUE;

            for (int node: notVisited) {
                if (nodeLowestDegree == Integer.MAX_VALUE) {
                    nodeLowestDegree = node;
                } else {
                    if (degree[node] < degree[nodeLowestDegree]) {
                        nodeLowestDegree = node;
                    }
                }
            }

            if (degree[nodeLowestDegree] > 1 || (degree[nodeLowestDegree] == 0 && notVisited.size() > 1)) {
                return false;
            }

            // remove it and adjust the adjacent nodes' degree
            notVisited.remove(nodeLowestDegree);
            
            // update adjacent nodes 
            for (int adjNode: map.get(nodeLowestDegree)) {
                degree[adjNode] --;
            }
        }

        return true;
    }
}
