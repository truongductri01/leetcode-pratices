class Solution {
    int m;
    int n;
    class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair other = (Pair) o;
            return other.row == this.row && other.col == other.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }

        public String toString() {
            return "(" + this.row + " " + this.col + ")";
        }
    }

    public boolean isValidPosition(int row, int col, int m, int n) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
    
    public int getArea(Pair start, Set<Pair> visited, int[][] grid, int[][] directions) {
        int area = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        
        while (!q.isEmpty()) {
            Pair current = q.remove();
            area ++;

            for (int[] dir: directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];
                Pair newPair = new Pair(newRow, newCol);
                if (isValidPosition(newRow, newCol, m, n) && !visited.contains(newPair) && grid[newRow][newCol] == 1) {
                    q.add(newPair);
                    visited.add(newPair);
                }
            }
        }
        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {
        /**
        Is the same as counting island, just different that we have to count the number 1 in them?
        */
        // set up the directions to move for BFS

        // Gather all pairs of 1
        // System.out.println("Start");
        List<Pair> ones = new ArrayList<>();
        this.m = grid.length;
        this.n = grid[0].length;
        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };
        Set<Pair> visited = new HashSet<>();

        for (int r = 0; r < grid.length; r ++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    // System.out.print("There is a 1");
                    ones.add(new Pair(r, c));
                }
            }
        }

        // conduct BFS on those pairs to count the area of an island
        int result = 0;
        // System.out.println(ones);

        // visited.add(new Pair(1, 1));
        // System.out.println(visited.contains(new Pair(1, 1)));
        for (Pair p: ones) {
            if (visited.contains(p)) {
                continue;
            }

            int area = getArea(p, visited, grid, directions);
            // System.out.print(p);
            // System.out.print(area);
            result = Math.max(result, area);
        }

        return result;
    }
}
