//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other. 
//
// 
//
// Given an integer n, return the number of distinct solutions to the n-queens puzzle. 
//
// Example: 
//
// 
//Input: 4
//Output: 2
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
//[
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
//

class Solution {
    int res = 0;
    public int totalNQueens(int n) {
        int res = 0;
        int[] queenColList = new int[n];
        return placeQueens(n, queenColList, 0);
    }
    public int placeQueens(int n, int[] queenColList, int row)
    {
        if(row == n)    res++;
        for(int col = 0; col < n; col++)
        {
            if(isValid(queenColList, row, col))
            {
                queenColList[row] = col;
                placeQueens(n, queenColList, row+1);
            }
        }
        return res;
    }
    private boolean isValid(int[] queenColList, int row, int col)
    {
        for(int i = 0; i < row; i++)
        {
            int j = queenColList[i];
            if(j == col)    return false;
            if((j - col) == (i - row))  return false;
            if((j - col) == -(i - row)) return false;
        }
        return true;
    }
}