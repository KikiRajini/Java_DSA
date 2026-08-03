package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class IslandAndTreasure {
    private final int INF = 2147483647;

    public void islandsAndTreasure(int[][] grid) {
        if (grid == null || grid.length == 0) return;

        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Step 1: Push ALL starting points (Treasure Chests '0') into the Queue
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    queue.add(new int[]{r, c});
                }
            }
        }

        // Standard 4-directional moves: {rowChange, colChange}
        // Down, Up, Right, Left
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Step 2: Standard BFS Traversal
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            // Look at all 4 neighbors using the directions array
            for (int[] dir : directions) {
                int nextRow = r + dir[0];
                int nextCol = c + dir[1];

                // Check bounds AND check if the neighbor is unvisited land ('INF')
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && grid[nextRow][nextCol] == INF) {

                    // The distance to neighbor = current cell's distance + 1
                    grid[nextRow][nextCol] = grid[r][c] + 1;

                    // Add neighbor to queue to continue the BFS wave
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
    }
}
