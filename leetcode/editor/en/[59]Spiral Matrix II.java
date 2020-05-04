//Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order. 
//
// Example: 
//
// 
//Input: 3
//Output:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]
// 
//

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for(int i = 1; i <= n*n; i++) {
            res[r][c] = i;
            int nr = r + dr[di], nc = c + dc[di];
            if(nr >= 0 && nc >= 0 && nr < n && nc < n && res[nr][nc] == 0) {
                r = nr;
                c = nc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return res;
    }
}