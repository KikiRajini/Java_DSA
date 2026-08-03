package graphs;

public class Islands {
    public int numIslands(char[][] grid) {
        int islands=0;
        int row = grid.length;
        int col = grid[0].length;

        for(int r=0; r<row; r++){
            for(int c=0; c<col; c++){
                if(grid[r][c]=='1'){
                    islands++;
                    neutraliseForThisIsland(r,c,grid);
                }
            }
        }
        return islands;
    }

    private void neutraliseForThisIsland(int r, int c, char[][] grid){
        if(r<0 || r>=grid.length || c<0 || c>=grid[0].length || grid[r][c]=='0'){
            return;
        }
        grid[r][c]='0';
        neutraliseForThisIsland(r+1,c,grid);
        neutraliseForThisIsland(r-1,c,grid);
        neutraliseForThisIsland(r,c+1,grid);
        neutraliseForThisIsland(r,c-1,grid);

    }
}
