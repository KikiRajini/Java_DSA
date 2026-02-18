package BinarySearch;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int ROWS = matrix.length; int COLS = matrix[0].length;
        int left = 0;
        int right = ROWS*COLS -1;

        while(left<=right){
            int middle = (right+left)/2;
            int row = middle / COLS;
            int col = middle % COLS;
            if(matrix[row][col]>target){
                right = middle -1;
            }else if(matrix[row][col]<target){
                left = middle +1;
            }else{
                return true;
            }
        }
        return false;
    }
}
