package graphs;

public class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int rows = board.length;
        int cols = board[0].length;

        // 1. Traverse the Borders to find "Safe" 'O's and mark them as 'T'

        // Top and Bottom borders
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == 'O') markSafe(0, c, board);
            if (board[rows - 1][c] == 'O') markSafe(rows - 1, c, board);
        }

        // Left and Right borders
        for (int r = 0; r < rows; r++) {
            if (board[r][0] == 'O') markSafe(r, 0, board);
            if (board[r][cols - 1] == 'O') markSafe(r, cols - 1, board);
        }

        // 2. Flip the board to its final state
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    // It was never reached from the border, so it's surrounded!
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    // It was reached from the border, so it's safe. Restore it.
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void markSafe(int r, int c, char[][] board) {
        // Base case: Out of bounds or NOT an 'O'
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != 'O') {
            return;
        }

        // Mark as Temporary / Safe
        board[r][c] = 'T';

        // DFS all 4 directions
        markSafe(r + 1, c, board);
        markSafe(r - 1, c, board);
        markSafe(r, c + 1, board);
        markSafe(r, c - 1, board);
    }
}
