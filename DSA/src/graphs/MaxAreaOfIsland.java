package graphs;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int row = grid.length;
        int col = grid[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 1) {
                    // Find the total area of the island connected to (r, c)
                    int currentIslandArea = neutraliseForThisIsland(r, c, grid);
                    maxArea = Math.max(maxArea, currentIslandArea);
                }
            }
        }
        return maxArea;
    }

    private int neutraliseForThisIsland(int r, int c, int[][] grid) {
        // Base Case: Out of bounds or water cell
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        // Mark as visited (sink the island)
        grid[r][c] = 0;

        // Total area = 1 (current cell) + sum of areas from all 4 directions
        int area = 1
                + neutraliseForThisIsland(r + 1, c, grid)  // Down
                + neutraliseForThisIsland(r - 1, c, grid)  // Up
                + neutraliseForThisIsland(r, c + 1, grid)  // Right
                + neutraliseForThisIsland(r, c - 1, grid); // Left

        return area;
    }
}
