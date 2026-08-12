package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAndAtlantic {

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            int rows = heights.length;
            int cols = heights[0].length;
            List<List<Integer>> result = new ArrayList<>();

            // boolean matrices to track which cells can reach the respective oceans
            boolean[][] pacific = new boolean[rows][cols];
            boolean[][] atlantic = new boolean[rows][cols];

            // 1. Traverse the Top and Bottom borders
            // Top border touches the Pacific, Bottom border touches the Atlantic
            for (int c = 0; c < cols; c++) {
                // Start DFS from the top row (Pacific ocean)
                dfs(heights, 0, c, heights[0][c], pacific);

                // Start DFS from the bottom row (Atlantic ocean)
                dfs(heights, rows - 1, c, heights[rows - 1][c], atlantic);
            }

            // 2. Traverse the Left and Right borders
            // Left border touches the Pacific, Right border touches the Atlantic
            for (int r = 0; r < rows; r++) {
                // Start DFS from the left column (Pacific ocean)
                dfs(heights, r, 0, heights[r][0], pacific);

                // Start DFS from the right column (Atlantic ocean)
                dfs(heights, r, cols - 1, heights[r][cols - 1], atlantic);
            }

            // 3. Find the intersection of both reachability matrices
            // If a cell is marked true in BOTH pacific and atlantic matrices,
            // it means water from that cell can flow to both oceans.
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (pacific[r][c] && atlantic[r][c]) {
                        result.add(Arrays.asList(r, c));
                    }
                }
            }

            return result;
        }


        private void dfs(int[][] heights, int r, int c, int prevHeight, boolean[][] visited) {
            // Base Cases for stopping the DFS:
            // 1. Out of grid boundaries (r < 0, c < 0, r >= rows, c >= cols)
            // 2. Cell has already been visited (visited[r][c] == true)
            // 3. The current cell is LOWER than the previous cell.
            //    (Since we are working backwards from the ocean, water must flow "down" to the ocean,
            //    meaning our backward search must only go "up" to equal or higher elevations).
            if (r >= heights.length || c >= heights[0].length || r < 0 || c < 0 || visited[r][c] || heights[r][c] < prevHeight) {
                return;
            }

            // Mark the current cell as reachable from the target ocean
            visited[r][c] = true;

            // Recursively check all 4 adjacent directions, passing the current cell's height as the new prevHeight
            dfs(heights, r + 1, c, heights[r][c], visited); // Down
            dfs(heights, r - 1, c, heights[r][c], visited); // Up
            dfs(heights, r, c + 1, heights[r][c], visited); // Right
            dfs(heights, r, c - 1, heights[r][c], visited); // Left
        }

}
