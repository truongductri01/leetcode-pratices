Problem: [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates/description/)

<strong>Solution: </strong>
```java
class Solution {
    class Position {
        int row;
        int col;
        int dist;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
            dist = 0;
        }
    }
    public void wallsAndGates(int[][] rooms) {
        /**
        Collect all the Gates and spread to all directions as long as the values of the rooms is different
        update the room's value if the distance from the current cell to the next room +1.
         */
        Queue<Position> q = new LinkedList<>();
        int rows = rooms.length;
        int cols = rooms[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rooms[r][c] == 0) {
                    q.add(new Position(r, c));
                }
            }
        }

        int[][] directions = {
            {-1, 0}, // up
            {1, 0}, // down
            {0, -1}, // left
            {0, 1} // right
        };

        while (!q.isEmpty()) {
            Position cur = q.remove();
            for (int[] d: directions) {
                int newRow = cur.row + d[0];
                int newCol = cur.col + d[1];
                boolean isValid = newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols;
                if (isValid) {
                    if (rooms[newRow][newCol] > 0 && rooms[newRow][newCol] > cur.dist + 1) {
                        Position newPos = new Position(newRow, newCol);
                        rooms[newRow][newCol] = cur.dist + 1;
                        newPos.dist = cur.dist + 1;
                        q.add(newPos);
                    }
                }
            }
        }
    }
}
```
