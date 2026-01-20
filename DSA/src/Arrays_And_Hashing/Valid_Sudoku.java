package Arrays_And_Hashing;

import java.util.HashSet;
import java.util.Set;

public class Valid_Sudoku {
/*You are given a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:
Each row must contain the digits 1-9 without duplicates.
Each column must contain the digits 1-9 without duplicates.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
Return true if the Sudoku board is valid, otherwise return false

Note: A board does not need to be full or be solvable to be valid.

Example 1:
Input: board =
[["1","2",".",".","3",".",".",".","."],
 ["4",".",".","5",".",".",".",".","."],
 [".","9","8",".",".",".",".",".","3"],
 ["5",".",".",".","6",".",".",".","4"],
 [".",".",".","8",".","3",".",".","5"],
 ["7",".",".",".","2",".",".",".","6"],
 [".",".",".",".",".",".","2",".","."],
 [".",".",".","4","1","9",".",".","8"],
 [".",".",".",".","8",".",".","7","9"]]

Output: true
Example 2:
Input: board =
[["1","2",".",".","3",".",".",".","."],
 ["4",".",".","5",".",".",".",".","."],
 [".","9","1",".",".",".",".",".","3"],
 ["5",".",".",".","6",".",".",".","4"],
 [".",".",".","8",".","3",".",".","5"],
 ["7",".",".",".","2",".",".",".","6"],
 [".",".",".",".",".",".","2",".","."],
 [".",".",".","4","1","9",".",".","8"],
 [".",".",".",".","8",".",".","7","9"]]

Output: false
Explanation: There are two 1's in the top-left 3x3 sub-box.*/
    class Solution {
        public boolean isValidSudoku(char[][] board) {

            Set<Character>[] rows = new HashSet[9];
            Set<Character>[] cols = new HashSet[9];
            Set<Character>[] boxes = new HashSet[9];

            // initialize sets ONCE
            for(int i=0;i<9;i++){
                rows[i] = new HashSet<>();
                cols[i] = new HashSet<>();
                boxes[i] = new HashSet<>();
            }

            for(int row = 0; row < 9; row++){
                for(int col = 0; col < 9; col++){

                    if(board[row][col]=='.') continue;

                    char val = board[row][col];
                    int boxnum = (row/3)*3 + (col/3);

                    if(rows[row].contains(val) ||
                            cols[col].contains(val) ||
                            boxes[boxnum].contains(val)){
                        return false;
                    }

                    rows[row].add(val);
                    cols[col].add(val);
                    boxes[boxnum].add(val);
                }
            }
            return true;
        }
    }

}
