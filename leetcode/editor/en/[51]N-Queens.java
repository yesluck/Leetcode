//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other. 
//
// 
//
// Given an integer n, return all distinct solutions to the n-queens puzzle. 
//
// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively. 
//
// Example: 
//
// 
//Input: 4
//Output: [
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
// 
//

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queenColList = new int[n];
        backtracking(res, queenColList, n, 0);
        return res;
    }

    // queenColList: at i-th row, the idx of the the column of that we put queen into.
    private void backtracking(List<List<String>> res, int[] queenColList, int n, int row) {
        if(row == n) {
            List<String> temp = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                String str = "";
                for(int j = 0; j < n; j++) {
                    if(j == queenColList[i])    str += "Q";
                    else    str += ".";
                }
                temp.add(str);
            }
            res.add(temp);
            return;
        }
        for(int j = 0; j < n; j++) {
            if(isValid(queenColList, row, j)) {
                queenColList[row] = j;
                backtracking(res, queenColList, n, row + 1);
            }
        }
    }

    private boolean isValid(int[] queenColList, int row, int col) {
        for(int i = 0; i < row; i++) {
            int j = queenColList[i];
            // start comparing (i,j) and (row,col)
            if(j == col)    return false;
            if(i - row == j - col)  return false;
            if(i - row == col - j)  return false;
        }
        return true;
    }
}