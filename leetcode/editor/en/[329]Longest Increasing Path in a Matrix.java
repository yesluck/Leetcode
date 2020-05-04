//Given an integer matrix, find the length of the longest increasing path. 
//
// From each cell, you can either move to four directions: left, right, up or do
//wn. You may NOT move diagonally or move outside of the boundary (i.e. wrap-aroun
//d is not allowed). 
//
// Example 1: 
//
// 
//Input: nums = 
//[
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//] 
//Output: 4 
//Explanation: The longest increasing path is [1, 2, 6, 9].
// 
//
// Example 2: 
//
// 
//Input: nums = 
//[
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//] 
//Output: 4 
//Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is
// not allowed.
// 
// Related Topics Depth-first Search Topological Sort Memoization


// DFS [TLE]
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        if (m == 0)
            return 0;
        n = matrix[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                res = Math.max(res, dfs(matrix, i, j) + 1);
        }
        return res;
    }

    public int dfs(int[][] matrix, int x, int y) {
        int max = 0;
        for (int[] dir:dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || matrix[nx][ny] <= matrix[x][y])
                continue;
            max = Math.max(max, dfs(matrix, nx, ny) + 1);
        }
        return max;
    }
}


// DFS with memoization
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m, n;
    int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        if (m == 0)
            return 0;
        n = matrix[0].length;

        memo = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(memo[i], -1);

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                res = Math.max(res, dfs(matrix, i, j) + 1);
        }
        return res;
    }

    public int dfs(int[][] matrix, int x, int y) {
        if (memo[x][y] > -1)
            return memo[x][y];

        int max = 0;
        for (int[] dir:dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || matrix[nx][ny] <= matrix[x][y])
                continue;
            max = Math.max(max, dfs(matrix, nx, ny) + 1);
        }
        memo[x][y] = max;
        return memo[x][y];
    }
}


// Topological Sort
public class Solution {
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        if (m == 0)
            return 0;
        n = matrix[0].length;

        // calculate outdegrees
        int[][] outdegree = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs){
                    int ni = i + dir[0], nj = j + dir[1];
                    if (ni < 0 || ni >= m || nj < 0 || nj >= n || matrix[ni][nj] <= matrix[i][j])
                        continue;
                    outdegree[i][j]++;
                }
            }
        }

        // find leaves who have zero out degree as the initial level
        List<int[]> leaves = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outdegree[i][j] == 0)
                    leaves.add(new int[]{i, j});
            }
        }

        // remove leaves level by level in topological order
        int res = 0;
        while (!leaves.isEmpty()) {
            res++;
            List<int[]> newLeaves = new ArrayList<>();
            for (int[] node : leaves) {
                int x = node[0], y = node[1];
                for (int[] dir:dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || matrix[nx][ny] >= matrix[x][y])
                        continue;
                    if (--outdegree[nx][ny] == 0)
                        newLeaves.add(new int[]{nx, ny});
                }
            }
            leaves = newLeaves;
        }
        return res;
    }
}