//Given a positive integer N, return the number of positive integers less than o
//r equal to N that have at least 1 repeated digit. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: 20
//Output: 1
//Explanation: The only positive number (<= 20) with at least 1 repeated digit i
//s 11.
// 
//
// 
// Example 2: 
//
// 
//Input: 100
//Output: 10
//Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 1
//1, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
// 
//
// 
// Example 3: 
//
// 
//Input: 1000
//Output: 262
// 
// 
//
// 
//
// Note: 
//
// 
// 1 <= N <= 10^9 
// 
// 
// Related Topics Math Dynamic Programming


class Solution {
    List<Integer> num;
    int[][] dp;
    int used;

    public int numDupDigitsAtMostN(int N) {
        int temp = N;
        num = new ArrayList<>();
        dp = new int[15][1 << 10];
        for (int i = 0; i < 15; i++)
            Arrays.fill(dp[i], -1);
        used = 0;

        while (N > 0) {
            num.add(N % 10);
            N /= 10;
        }
        return temp - dfs(num.size(), 0, true, true);
    }

    public int dfs(int pos, int used, boolean limit, boolean lead) {
        if (pos == 0)
            return lead ? 0 : 1;
        if (!lead && !limit && dp[pos][used] != -1)
            return dp[pos][used];

        int up = limit ? num.get(pos - 1) : 9;

        int res = 0;
        for (int i = 0; i <= up; i++) {
            if (((1 << i) & used) == 0) {
                if (lead && (i == 0))
                    res += dfs(pos - 1, used, false, true);
                else
                    res += dfs(pos - 1, used | (1 << i), limit && (i == up), false);
            }
        }

        dp[pos][used] = res;

        return res;
    }
}
