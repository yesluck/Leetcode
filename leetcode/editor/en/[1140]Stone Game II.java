//Alex and Lee continue their games with piles of stones. There are a number of 
//piles arranged in a row, and each pile has a positive integer number of stones p
//iles[i]. The objective of the game is to end with the most stones. 
//
// Alex and Lee take turns, with Alex starting first. Initially, M = 1. 
//
// On each player's turn, that player can take all the stones in the first X rem
//aining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). 
//
// The game continues until all the stones have been taken. 
//
// Assuming Alex and Lee play optimally, return the maximum number of stones Ale
//x can get. 
//
// 
// Example 1: 
//
// 
//Input: piles = [2,7,9,4,4]
//Output: 10
//Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, th
//en Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex
// takes two piles at the beginning, then Lee can take all three piles left. In th
//is case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
// 
//
// 
// Constraints: 
//
// 
// 1 <= piles.length <= 100 
// 1 <= piles[i] <= 10 ^ 4 
// Related Topics Dynamic Programming


class Solution {
    int[][] memo;
    int[] sumToEnd;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        memo = new int[n][n];
        sumToEnd = new int[n];

        sumToEnd[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--)
            sumToEnd[i] = sumToEnd[i + 1] + piles[i];

        return minimax(piles, 0, 1);
    }

    public int minimax(int[] piles, int pos, int M) {
        int n = piles.length;

        if (pos + 2 * M >= n)
            return sumToEnd[pos];

        if (memo[pos][M] > 0)
            return memo[pos][M];

        for (int X = 1; X <= 2 * M; X++)
            memo[pos][M] = Math.max(memo[pos][M], sumToEnd[pos] - minimax(piles, pos + X, Math.max(M, X)));

        return memo[pos][M];
    }
}
