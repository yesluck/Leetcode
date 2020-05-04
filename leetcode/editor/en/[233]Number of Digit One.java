//Given an integer n, count the total number of digit 1 appearing in all non-neg
//ative integers less than or equal to n. 
//
// Example: 
//
// 
//Input: 13
//Output: 6 
//Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
// 
// Related Topics Math


class Solution {
    List<Integer> num;
    int[][] dp;

    public int countDigitOne(int n) {
        num = new ArrayList<>();
        dp = new int[15][15];
        for (int i = 0; i < 15; i++)
            Arrays.fill(dp[i], -1);

        while (n > 0) {
            num.add(n % 10);
            n /= 10;
        }
        return dfs(num.size(), 0, true);
    }

    public int dfs(int pos, int state, boolean limit) {
        if (pos == 0)
            return state;
        if (!limit && dp[pos][state] != -1)
            return dp[pos][state];

        int up = limit ? num.get(pos - 1) : 9;

        int res = 0;
        for (int i = 0; i <= up; i++)
            res += dfs(pos - 1, state + (i == 1 ? 1 : 0), limit && (i == up));

        dp[pos][state] = res;

        return res;
    }
}