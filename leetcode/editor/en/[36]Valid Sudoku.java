//Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules: 
//
// 
// Each row must contain the digits 1-9 without repetition. 
// Each column must contain the digits 1-9 without repetition. 
// Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition. 
// 
//
// 
//A partially filled sudoku which is valid. 
//
// The Sudoku board could be partially filled, where empty cells are filled with the character '.'. 
//
// Example 1: 
//
// 
//Input:
//[
//  ["5","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//Output: true
// 
//
// Example 2: 
//
// 
//Input:
//[
//  ["8","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//Output: false
//Explanation: Same as Example 1, except with the 5 in the top left corner being 
//    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
// 
//
// Note: 
//
// 
// A Sudoku board (partially filled) could be valid but is not necessarily solvable. 
// Only the filled cells need to be validated according to the mentioned rules. 
// The given board contain only digits 1-9 and the character '.'. 
// The given board size is always 9x9. 
// 
//

// less time, tricky cube
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            Set<Character> row = new HashSet<>();
            Set<Character> col = new HashSet<>();
            Set<Character> cub = new HashSet<>();
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.' && !row.add(board[i][j])) return false;
                if(board[j][i] != '.' && !col.add(board[j][i])) return false;

                int rowIdx = 3 * (i / 3), colIdx = 3 * (i % 3);
                if(board[rowIdx + j / 3][colIdx + j % 3] != '.' && !cub.add(board[rowIdx + j / 3][colIdx + j % 3]))   return false;
            }
        }
        return true;
    }
}

// less space
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.')  continue;
                if(!valid(board, i, j)) return false;
            }
        }
        return true;
    }
    private boolean valid(char[][] board, int row, int col) {
        for(int i = 0; i < 9; i++) {
            if(i == row)    continue;
            if(board[i][col] != '.' && board[i][col] == board[row][col])    return false;
        }
        for(int j = 0; j < 9; j++) {
            if(j == col)    continue;
            if(board[row][j] != '.' && board[row][j] == board[row][col])    return false;
        }
        for(int i = 3 * (row / 3); i < 3 * (row / 3 + 1); i++) {
            for(int j = 3 * (col / 3); j < 3 * (col / 3 + 1); j++) {
                if(i == row && j == col)    continue;
                if(board[i][j] != '.' && board[i][j] == board[row][col])    return false;
            }
        }
        return true;
    }
}