//On an NxN chessboard, a knight starts at the r-th row and c-th column and atte
//mpts to make exactly K moves. The rows and columns are 0 indexed, so the top-lef
//t square is (0, 0), and the bottom-right square is (N-1, N-1). 
//
// A chess knight has 8 possible moves it can make, as illustrated below. Each m
//ove is two squares in a cardinal direction, then one square in an orthogonal dir
//ection. 
//
// 
//
// 
//
// 
//
// Each time the knight is to move, it chooses one of eight possible moves unifo
//rmly at random (even if the piece would go off the chessboard) and moves there. 
//
//
// The knight continues moving until it has made exactly K moves or has moved of
//f the chessboard. Return the probability that the knight remains on the board af
//ter it has stopped moving. 
//
// 
//
// Example: 
//
// 
//Input: 3, 2, 0, 0
//Output: 0.0625
//Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight o
//n the board.
//From each of those positions, there are also two moves that will keep the knig
//ht on the board.
//The total probability the knight stays on the board is 0.0625.
// 
//
// 
//
// Note: 
//
// 
// N will be between 1 and 25. 
// K will be between 0 and 100. 
// The knight always initially starts on the board. 
// 
// Related Topics Dynamic Programming


class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] dir = {{2,1}, {1,2}, {-1,2}, {-2,1}, {1,-2}, {2,-1}, {-1,-2}, {-2,-1}};

        double[][] dp = new double[N][N];
        dp[r][c] = 1.0;
        for (int i = 0; i < K; i++) {
            double[][] temp = new double[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int d = 0; d < 8; d++) {
                        int nj = j + dir[d][0], nk = k + dir[d][1];
                        if (nj >= 0 && nj < N && nk >= 0 && nk < N)
                            temp[nj][nk] += dp[j][k] / 8.0;
                    }
                }
            }
            dp = temp;
        }

        double res = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                res += dp[i][j];
            }
        }

        return res;
    }
}