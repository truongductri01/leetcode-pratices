Source: [here](https://leetcode.com/problems/network-delay-time/description/)

## 1. Understand the problem

The problem is to determine how long will it take for the signal to be sent to all nodes in the graph (network). This also means determining the shortest distance from the source to all the other nodes

Sound familiar, right? We can use a single-source shortest-distance algorithm to solve this one.

There are 2 algorithms we can choose to use: Dijkstra or Bellman-Ford. In our case, no need to use Bellman-Ford since there are no negative weight edges in the network. Before jumping into the Dijkstra solution, let's explore one alternative but simpler solution to understand.

## First approach: BFS

Imagine spreading the signal from the source to all adjacent nodes, then from those adjacent nodes to their adjacent ones, and continuing. While spreading like that, we can keep track of the distance so far from the source to the nodes. If a node is not yet reached (there is no path going through it yet], the value will be Infinity.

What if multiple nodes point to a single one? We will just take the one with the smallest relaxation value.
![image](https://github.com/truongductri01/leetcode-pratices/assets/58579187/70b49430-ec79-4bcf-a160-25f69986a963)


What is that?

We define that if there is an edge from y -> x: `relaxation value of x = min of {dist from the source of x, dist from the source of y + weight(y -> x)}`

In the end, we will collect the shortest distance from the source node to all the other nodes.

If there is still an infinity value among those distances, that means one of the node is not reachable from the nodes using the spreading method. We will return -1. Otherwise, return the max value among the distances.

Here is the code: 
``` java
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        // TODO: create a map representing the distance from the source node to any other node.
        // map.get(x) means the distance from node k -> x. Therefore k -> k = 0
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= n; i ++) {
            dist.put(i, Integer.MAX_VALUE);
        }
        dist.put(k, 0);

        // TODO: a map represent the graph. with graph.get(x) shows another map storing the adjacent nodes and the weights of the edge.
        // So if graph.get(x).get(y) = w, then the weight of edge x -> y is w.
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge: times) {
            int source = edge[0];
            int dest = edge[1];
            int weight = edge[2];
            graph.putIfAbsent(source, new HashMap<>());
            graph.get(source).put(dest, weight);
        }

        // start BFS
        Queue<Integer> bfs = new LinkedList<>();
        bfs.add(k);

        while (!bfs.isEmpty()) {
            int top = bfs.remove();
            if (!graph.containsKey(top)) {
                continue;
            }
            for (int adjNode: graph.get(top).keySet()) {
                int newValue = dist.get(top) + graph.get(top).get(adjNode);
                if (newValue < dist.get(adjNode)) {
                    bfs.add(adjNode);
                    dist.put(adjNode, newValue);
                }
            }
        }


        // TODO: check the max value
        int max = 0;
        for (int node: dist.keySet()) {
            if (dist.get(node) == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, dist.get(node));
        }
        return max;
    }
}
```


## Second approach: Dijkstra
Similar to the previous code, we still maintain the map for distance and edges. However, since Dijkstra utilize a min heap to keep track of the smallest weight that we have so far at a specific nodes, we will also use a Head to solve it.

Here is the code:
```java
class Solution {
    class Node {
        int nodeIdx;
        int value;

        Node(int nodeIdx, int value) {
            this.nodeIdx = nodeIdx;
            this.value = value;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        /*
        Find the shortest path from a single source in a directed graph.
        
        1. Check if there is a cycle? Maybe yes
        2. All weights are positive
        3. times.length = 6000

        We might be able to use Dijkstra to solve this problem

        Start with a node:
        - relax the adjacent node
        - repeat the project until all nodes are visited
        - return the one with the maximum value so far.

        Implement:
        1. Have a map to keep track of the distance from the source to the current node

        map[a]: int => dist from source to a

        map[b] can be : Math.min(map[b], map[c] + dist(c, b))

        2. Have a map to store the distance between the nodes. Since this is directed edges, we just need to do it for 1 way

        3. Initialize a Priority Queue for retrieve the3 values of the smallest edge. How to

        If at somepoint, the priority queue is empty but not all nodes are visited, return -1. Otherwise, return the max value so far => store the max value 
        */
        Map<Integer, Integer> distFromSource = new HashMap<>();
        // one obvious initial
        for (int i = 1; i <= n; i++) {
            distFromSource.put(i, Integer.MAX_VALUE);
        }
        distFromSource.put(k, 0);


        Map<Integer, Map<Integer, Integer>> graphRepresent = new HashMap<>();

        // to store the edge and weight
        for (int[] edge: times) {
            int source = edge[0];
            int dest = edge[1];
            int weight = edge[2];
            graphRepresent.putIfAbsent(source, new HashMap<>());
            graphRepresent.get(source).put(dest, weight);
        }

        // start the Dijkstra
        int maxValue = 0;
        PriorityQueue<Node> dijkstra = new PriorityQueue<>((a, b) -> (a.value - b.value));
        Set<Integer> visited = new HashSet<>();

        dijkstra.add(new Node(k, 0));
        visited.add(k);

        while (!dijkstra.isEmpty() && visited.size() < n) {
            Node node = dijkstra.remove();
            maxValue = Math.max(maxValue, node.value);
            visited.add(node.nodeIdx);
            // go through the adjacent nodes to do the relaxing part
            
            if (!graphRepresent.containsKey(node.nodeIdx)) {
                continue;
            }
            for (int adjNode: graphRepresent.get(node.nodeIdx).keySet()) {
                if (!visited.contains(adjNode)) {
                    int newValue = distFromSource.get(node.nodeIdx) + graphRepresent.get(node.nodeIdx).get(adjNode);
                    if (newValue < distFromSource.get(adjNode)) {
                        distFromSource.put(adjNode, newValue);
                        dijkstra.add(new Node(adjNode, newValue));
                    }
                    
                }
            }
        }

        if (visited.size() < n) {
            return -1;
        }

        return maxValue;
    }
}
```
