//A chess knight can move as indicated in the chess diagram below: 
//
// . 
//
// 
//
// This time, we place our chess knight on any numbered key of a phone pad (indi
//cated above), and the knight makes N-1 hops. Each hop must be from one key to an
//other numbered key. 
//
// Each time it lands on a key (including the initial placement of the knight), 
//it presses the number of that key, pressing N digits total. 
//
// How many distinct numbers can you dial in this manner? 
//
// Since the answer may be large, output the answer modulo 10^9 + 7. 
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
//Input: 1
//Output: 10
// 
//
// 
// Example 2: 
//
// 
//Input: 2
//Output: 20
// 
//
// 
// Example 3: 
//
// 
//Input: 3
//Output: 46
// 
//
// 
//
// Note: 
//
// 
// 1 <= N <= 5000 
// 
// 
// 
// 
// Related Topics Dynamic Programming


class Solution {
    int[][] map;
    int kMod = (int)Math.pow(10, 9) + 7;

    public int knightDialer(int N) {
        map = new int[10][3];
        map[1][0] = 6; map[1][1] = 8; map[1][2] = Integer.MAX_VALUE;
        map[2][0] = 7; map[2][1] = 9; map[2][2] = Integer.MAX_VALUE;
        map[3][0] = 4; map[3][1] = 8; map[3][2] = Integer.MAX_VALUE;
        map[4][0] = 3; map[4][1] = 9; map[4][2] = 0;
        map[5][0] = Integer.MAX_VALUE; map[5][1] = Integer.MAX_VALUE; map[5][2] = Integer.MAX_VALUE;
        map[6][0] = 1; map[6][1] = 7; map[6][2] = 0;
        map[7][0] = 6; map[7][1] = 2; map[7][2] = Integer.MAX_VALUE;
        map[8][0] = 1; map[8][1] = 3; map[8][2] = Integer.MAX_VALUE;
        map[9][0] = 2; map[9][1] = 4; map[9][2] = Integer.MAX_VALUE;
        map[0][0] = 4; map[0][1] = 6; map[0][2] = Integer.MAX_VALUE;

        int[] dp = new int[10];
        Arrays.fill(dp, 1);

        int res = 0;
        for (int i = 1; i < N; i++) {
            int[] temp = new int[10];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 3; k++) {
                    temp[j] += map[j][k] < 10 ? dp[map[j][k]] : 0;
                    temp[j] %= kMod;
                }
            }
            dp = temp;
        }

        for (int i = 0; i < 10; i++) {
            res += dp[i];
            res %= kMod;
        }

        return res;
    }
}
