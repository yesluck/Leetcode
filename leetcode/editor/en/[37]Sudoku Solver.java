//Write a program to solve a Sudoku puzzle by filling the empty cells. 
//
// A sudoku solution must satisfy all of the following rules: 
//
// 
// Each of the digits 1-9 must occur exactly once in each row. 
// Each of the digits 1-9 must occur exactly once in each column. 
// Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid. 
// 
//
// Empty cells are indicated by the character '.'. 
//
// 
//A sudoku puzzle... 
//
// 
//...and its solution numbers marked in red. 
//
// Note: 
//
// 
// The given board contain only digits 1-9 and the character '.'. 
// You may assume that the given Sudoku puzzle will have a single unique solution. 
// The given board size is always 9x9. 
// 
//

// backtracking
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.')  continue;
                for(char c = '1'; c <= '9'; c++) {
                    if(!valid(board, i, j, c))  continue;
                    board[i][j] = c;
                    if(solve(board))    return true;
                    else    board[i][j] = '.';
                }
                return false;
            }
        }
        return true;
    }

    // can also be converted to the tricky way
    private boolean valid(char[][] board, int row, int col, char c) {
        for(int i = 0; i < 9; i++) {
            if(i == row)    continue;
            if(board[i][col] != '.' && board[i][col] == c)  return false;
        }
        for(int j = 0; j < 9; j++) {
            if(j == col)    continue;
            if(board[row][j] != '.' && board[row][j] == c)  return false;
        }
        for(int i = 3 * (row / 3); i < 3 * (row / 3 + 1); i++) {
            for(int j = 3 * (col / 3); j < 3 * (col / 3 + 1); j++) {
                if(i == row && j == col)    continue;
                if(board[i][j] != '.' && board[i][j] == c)  return false;
            }
        }
        return true;
    }
}