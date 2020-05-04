//Given a 2D board and a word, find if the word exists in the grid. 
//
// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once. 
//
// Example: 
//
// 
//board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//Given word = "ABCCED", return true.
//Given word = "SEE", return true.
//Given word = "ABCB", return false.
// 
//
// 
// Constraints: 
//
// 
// board and word consists only of lowercase and uppercase English letters. 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
//

class Solution {
    public boolean[][] used;
    public int counter;
    public int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int m, n;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        if (m == 0)
            return false;
        n = board[0].length;
        if (n == 0)
            return false;
        used = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    used[i][j] = true;
                    counter = 1;
                    if (backtracking(board, word, i, j))
                        return true;
                    counter = 0;
                    used[i][j] = false;
                }
            }
        }
        return false;
    }

    public boolean backtracking(char[][] board, String word, int i, int j) {
        if (counter == word.length())
            return true;
        for (int d = 0; d < 4; d++) {
            int ni = i + direction[d][0], nj = j + direction[d][1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && !used[ni][nj]) {
                if (board[ni][nj] == word.charAt(counter)) {
                    used[ni][nj] = true;
                    counter += 1;
                    if (backtracking(board, word, ni, nj))
                        return true;
                    counter -= 1;
                    used[ni][nj] = false;
                }
            }
        }
        return false;
    }
}