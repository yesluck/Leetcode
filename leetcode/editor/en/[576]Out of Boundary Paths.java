//There is an m by n grid with a ball. Given the start coordinate (i,j) of the b
//all, you can move the ball to adjacent cell or cross the grid boundary in four d
//irections (up, down, left, right). However, you can at most move N times. Find o
//ut the number of paths to move the ball out of grid boundary. The answer may be 
//very large, return it after mod 109 + 7. 
//
// 
//
// Example 1: 
//
// 
//Input: m = 2, n = 2, N = 2, i = 0, j = 0
//Output: 6
//Explanation:
//
// 
//
// Example 2: 
//
// 
//Input: m = 1, n = 3, N = 3, i = 0, j = 1
//Output: 12
//Explanation:
//
// 
//
// 
//
// Note: 
//
// 
// Once you move the ball out of boundary, you cannot move it back. 
// The length and height of the grid is in range [1,50]. 
// N is in range [0,50]. 
// 
// Related Topics Dynamic Programming Depth-first Search


// Recursion with memoization [TLE]
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int kMod = (int)1e9 + 7;
    int[][][] memo;

    public int findPaths(int m, int n, int N, int i, int j) {
        memo = new int[m][n][N + 1];
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < n; l++)
                Arrays.fill(memo[k][l], -1);
        }
        return helper(m, n, N, i, j);
    }

    public int helper(int m, int n, int N, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return 1;

        if (N == 0)
            return 0;

        if (memo[i][j][N] >= 0)
            return memo[i][j][N];

        int temp = 0;
        for (int[] dir : dirs) {
            int ni = i + dir[0], nj = j + dir[1];
            temp += helper(m, n, N - 1, ni, nj) % kMod;
            temp %= kMod;
        }

        memo[i][j][N] = temp;
        return memo[i][j][N];
    }
}


// DP ~688
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int kMod = (int)1e9 + 7;

    public int findPaths(int m, int n, int N, int x, int y) {
        int res = 0;
        int[][] dp = new int[m][n];
        dp[x][y] = 1;

        for (int i = 0; i < N; i++) {
            int[][] temp = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    for (int[] dir :dirs) {
                        int nj = j + dir[0], nk = k + dir[1];
                        if (nj >= 0 && nj < m && nk >= 0 && nk < n) {
                            temp[nj][nk] = (temp[nj][nk] + dp[j][k]) % kMod;
                        } else
                            res = (res + dp[j][k]) % kMod;
                    }
                }
            }
            dp = temp;
        }

        return res;
    }
}
