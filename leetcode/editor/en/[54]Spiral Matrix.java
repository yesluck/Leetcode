//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order. 
//
// Example 1: 
//
// 
//Input:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//Output: [1,2,3,6,9,8,7,4,5]
// 
//
// Example 2: 
// 
//Input:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]
//

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        if(m == 0)  return res;
        int n = matrix[0].length;

        boolean[][] seen = new boolean[m][n];
        int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for(int i = 0; i < m*n; i++) {
            res.add(matrix[r][c]);
            seen[r][c] = true;
            int next_r = r + dr[di], next_c = c + dc[di];
            if(next_r >= 0 && next_r < m && next_c >= 0 && next_c < n && !seen[next_r][next_c]) {
                r = next_r;
                c = next_c;
            } else {
                di = (di + 1) % 4;
                r = r + dr[di];
                c = c + dc[di];
            }
        }
        return res;
    }
}