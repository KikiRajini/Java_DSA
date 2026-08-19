package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // Step 1: Add all initially rotten oranges to queue & count fresh ones
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.add(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        // If no fresh oranges exist from the start, 0 minutes have passed
        if (freshCount == 0) return 0;

        int minutes = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Step 2: Level-by-level BFS traversal
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size(); // Process current minute's layer completely

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];

                for (int[] dir : directions) {
                    int nextRow = r + dir[0];
                    int nextCol = c + dir[1];

                    // Infect valid adjacent fresh oranges
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                            && grid[nextRow][nextCol] == 1) {

                        grid[nextRow][nextCol] = 2; // Mark rotten
                        freshCount--;               // One less fresh orange
                        queue.add(new int[]{nextRow, nextCol});
                    }
                }
            }
            minutes++; // One full minute/layer processed
        }

        // Step 3: If any fresh orange remains unreachable, return -1
        return freshCount == 0 ? minutes : -1;
    }
}
