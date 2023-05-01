class Solution {
    public boolean isValidRow(char[][] board, int rowIdx) {
        Set<Character> charSet = new HashSet<>();

        for (char c: board[rowIdx]) {
            if (c != '.') {
                if (charSet.contains(c)) {
                    return false;
                }
                charSet.add(c);
            }
        }
        return true;
    }

    public boolean isValidCol(char[][] board, int colIdx) {
        Set<Character> charSet = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            char c = board[i][colIdx];
            if (c != '.'){
                if (charSet.contains(c)) {
                    return false;
                }
                charSet.add(c);
            }
        }
        return true;
    }

    public boolean isValidBox(char[][] board, int rowIdx, int colIdx) {
        Set<Character> charSet = new HashSet<>();
        for (int r = rowIdx; r < rowIdx + 3; r++) {
            for (int c = colIdx; c < colIdx + 3; c++) {
                char ch = board[r][c];
                if (ch != '.'){
                    if (charSet.contains(ch)) {
                        return false;
                    }
                    charSet.add(ch);
                }
            }
        }

        return true;
    }
    public boolean isValidSudoku(char[][] board) {
        // check row, check columns, then check box of 3
        /**
        How to check the box of 3?
        - know the start row and column
         */

        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i) || !isValidCol(board, i)) {
                return false;
            }
        }

        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                if (!isValidBox(board, r, c)) {
                    return false;
                }
            }
        }

        return true;
    }
}
