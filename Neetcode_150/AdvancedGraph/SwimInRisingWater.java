class Solution {
    int[][] directions = {
        // up
        {-1, 0},
        // down
        {1, 0},
        // left 
        {0, -1},
        // right
        {0, 1}
    };

    public boolean inGrid(int[][] grid, int row, int col) {
        int n = grid.length;
        return row >= 0 && col >= 0 && row < n && col < n;
    }

    class Position {
        int row;
        int col;
        int val;

        public Position(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position myObj = (Position) o;
            return myObj.row == this.row && myObj.col == this.col && myObj.val == this.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, val);
        }

        // public String toString() {
        //     return "Position(" + row + ", " + col + ", " + val + ")";
        // }
    }

    public int swimInWater(int[][] grid) {
        /**
        What can be improve is to find the shortest path to goes from the beginnig to the end of the cells

        We can applies Kruskal's algorithms:
        - Start with the start,
        - Check adjacents cell and add those into a heap,
        - Pop the one at the top of the min heap to check. Then check the adjacents cell that is not visited of that cell
        - Untill we reach the bottom right cell, stop and return.

        We will have a list of visited Position, return the max value amongs those. 
        */
        int n = grid.length;
        Set<Position> visited = new HashSet<>();
        PriorityQueue<Position> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        Position start = new Position(0, 0, grid[0][0]);
        pq.add(start);
        visited.add(start);

        int result = start.val;

        while (!pq.isEmpty()) {
            Position current = pq.remove();
            // System.out.println(current);

            result = Math.max(result, current.val);

            if (current.row == n - 1 && current.col == n - 1) {
                // System.out.println("New Result value")
                break;
            }

            for (int[] dir: directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];
                if (inGrid(grid, newRow, newCol)) {
                    int newVal = grid[newRow][newCol];
                    Position adjPos = new Position(newRow, newCol, newVal);
                    if (!visited.contains(adjPos)) {
                        pq.add(adjPos);
                        visited.add(adjPos);
                    }
                }
            }
        }

        return result;
        
    }
}
