class Solution {
    public List<Integer> getFirstPair(Map<Integer, Set<Integer>> map) {
        List<Integer> result = new ArrayList<>();
        for (int key: map.keySet()) {
            for (int value: map.get(key)) {
                result.add(key);
                result.add(value);
                break;
            }
            break;
        }
        return result;
    }

    public boolean isValidPosition(int r, int c, int m, int n) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    // public void printMap(Map<Integer, Set<Integer>> map) {
    //     for (int key: map.keySet()) {
    //         System.out.printf("%d: {", key);
    //         for (int value: map.get(key)) {
    //             System.out.printf("%d ", value);
    //         }
    //         System.out.println("}");
    //     }
    // }

    public void removeNode(Map<Integer, Set<Integer>> map, int row, int col) {
        map.get(row).remove(col);
        if (map.get(row).isEmpty()) {
            map.remove(row);
        }
    }

    public int numIslands(char[][] grid) {
        /**
        We can apply BFS in search for an island 

        Thought:
        - goes through the array and store the position of the number 1 in the list
            Using map and set together: row 0: set(column index)
        - Take the first element from the map and start BFS, remove the node that is visited
            Increase count when the queue for BFS is cleared
        - Repeat until the map is empty


        Visit at most all the nodes in the array: O(m * n)
        Store at most all nodes in the array: O(m * n)
         */
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {
            {-1, 0}, // up x -1, y
            {1, 0}, // down
            {0, -1}, // left
            {0, 1} // right
        };
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == '1') {
                    visited.putIfAbsent(r, new HashSet<>());
                    visited.get(r).add(c);
                }
            }
        }
        // printMap(visited);

        while (!visited.isEmpty()) {
            // printMap(visited);
            Queue<List<Integer>> q = new LinkedList<>();
            List<Integer> firstPair = getFirstPair(visited);
            if (firstPair.size() == 0) {
                System.out.println("No more pair to explore");
                break;
            }
            // System.out.println(firstPair);
            removeNode(visited, firstPair.get(0), firstPair.get(1));
            q.add(firstPair);

            while (!q.isEmpty()) {
                List<Integer> currentPair = q.remove();
                int currentRow = currentPair.get(0);
                int currentCol = currentPair.get(1);
                // check all 4 direction
                for (int[] dir: directions) {
                    int newRow = currentRow + dir[0];
                    int newCol = currentCol + dir[1];
                    if (isValidPosition(newRow, newCol, m, n) && 
                            visited.containsKey(newRow) && 
                            visited.get(newRow).contains(newCol)) {
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(newRow);
                        newPair.add(newCol);
                        q.add(newPair);
                        removeNode(visited, newRow, newCol);
                    }
                }
            }
            count ++;
        }
        return count;
    }
}
