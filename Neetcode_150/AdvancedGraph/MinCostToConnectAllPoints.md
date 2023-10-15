Problem: [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/description/)

<strong>Explain</strong>

Looking at the problem, we can identify this as a Graph problem. And in order to find the minimum cost to connect all points, we need to establish the minimum spanning tree.
We can either use Kruskal's or Prim's algorithm. I will go with Prim's algorithm for this solution.

For Prim's algo, we will create a minimum spanning tree by expanding from 1 node, adding new nodes which has the smallest edge and repeat until we reach the final result.
We will need a min heap to keep track of the edge with the smallest distance value.

In the code, you will see a while loop, the condition to break that is either the heap is empty or we already have all nodes marked as visited.



<strong>Solution</strong>
```java
class Solution {
    class Edge {
        int[] p1;
        int[] p2;
        int distance;
        int index1;
        int index2;

        public Edge (int[] p1, int index1, int[] p2, int index2) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
            this.index1 = index1;
            this.index2 = index2;
        }
    }
    
    public int minCostConnectPoints(int[][] points) {
        if (points.length == 1) {
            return 0;
        }
        // Start with any points, add all possible path into a Queue
        // take out the smallest distance in the queue such that one node is in the connected and on is not
        // only add the edges comes after the index

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        int[] parents = new int[points.length];
        Set<Integer> visited = new HashSet<>();
        Set<Integer> notVisited = new HashSet<>();

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            notVisited.add(i);
        }
        visited.add(0);
        notVisited.remove(0);


        int result = 0;

        for (int i = 1; i < points.length; i++) {
            // create the Edge
            Edge edge = new Edge(points[0], 0, points[i], i);
            pq.add(edge);
        }

        while (!pq.isEmpty() && visited.size() < points.length) {
            // take the first pair
            Edge edge = pq.remove();

            // make sure they do not create a cycle.

            if (visited.contains(edge.index1) && visited.contains(edge.index2)) {
                continue;
            } else {
                result += edge.distance;
                for (int i: notVisited) {
                    Edge newEdge = new Edge(points[edge.index2], edge.index2, points[i], i);
                    pq.add(newEdge);
                }
                visited.add(edge.index2);
                notVisited.remove(edge.index2);
            }
        }

        return result;
    }
}
```
