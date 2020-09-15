//You are given K eggs, and you have access to a building with N floors from 1 t
//o N. 
//
// Each egg is identical in function, and if an egg breaks, you cannot drop it a
//gain. 
//
// You know that there exists a floor F with 0 <= F <= N such that any egg dropp
//ed at a floor higher than F will break, and any egg dropped at or below floor F 
//will not break. 
//
// Each move, you may take an egg (if you have an unbroken one) and drop it from
// any floor X (with 1 <= X <= N). 
//
// Your goal is to know with certainty what the value of F is. 
//
// What is the minimum number of moves that you need to know with certainty what
// F is, regardless of the initial value of F? 
//
// 
//
// 
// 
//
// 
// Example 1: 
//
// 
//Input: K = 1, N = 2
//Output: 2
//Explanation: 
//Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
//Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty th
//at F = 1.
//If it didn't break, then we know with certainty F = 2.
//Hence, we needed 2 moves in the worst case to know what F is with certainty.
// 
//
// 
// Example 2: 
//
// 
//Input: K = 2, N = 6
//Output: 3
// 
//
// 
// Example 3: 
//
// 
//Input: K = 3, N = 14
//Output: 4
// 
//
// 
//
// Note: 
//
// 
// 1 <= K <= 100 
// 1 <= N <= 10000 
// 
// 
// 
// 
// Related Topics Math Binary Search Dynamic Programming


// O(KN2) TLE
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];

        for (int k0 = 1; k0 <= K; k0++)
            dp[k0][1] = 1;
        for (int n0 = 1; n0 <= N; n0++)
            dp[1][n0] = n0;

        for (int k0 = 2; k0 <= K; k0++) {
            for (int n0 = 2; n0 <= N; n0++) {
                int minVal = Integer.MAX_VALUE;
                for (int x = 1; x <= n0; x++) {
                    int temp = Math.max(dp[k0][n0 - x], dp[k0 - 1][x - 1]);
                    if (temp < minVal)
                        minVal = temp;
                }
                dp[k0][n0] = minVal + 1;
            }
        }

        return dp[K][N];
    }
}

// Binary Search for X O(KNlogN)
class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int superEggDrop(int K, int N) {
        return dp(K, N);
    }

    public int dp(int K, int N) {
        if (!memo.containsKey(N * 100 + K)) {
            if (N == 0)
                return 0;
            if (N == 1)
                return 1;
            if (K == 1)
                return N;

            int res;
            int left = 1, right = N + 1;
            while (left < right) {
                int x = left + (right - left) / 2;
                int t1 = dp(K, N - x), t2 = dp(K - 1, x - 1);
                if (t1 < t2)
                    right = x;
                else if (t2 < t1)
                    left = x + 1;
                else
                    left = right = x;
            }

            res = 1 + Math.min(Math.max(dp(K - 1, left - 1), dp(K, N - left)),
                    Math.max(dp(K - 1, right - 1), dp(K, N - right)));
            memo.put(N * 100 + K, res);
        }

        return memo.get(N * 100 + K);
    }
}

// Reverse DP O(kN)
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            m += 1;
            for (int k0 = 1; k0 <= K; k0++)
                dp[m][k0] = dp[m - 1][k0 - 1] + dp[m - 1][k0] + 1;
        }
        return m;
    }
}