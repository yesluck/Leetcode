//We have a sorted set of digits D, a non-empty subset of {'1','2','3','4','5','
//6','7','8','9'}. (Note that '0' is not included.) 
//
// Now, we write numbers using these digits, using each digit as many times as w
//e want. For example, if D = {'1','3','5'}, we may write numbers such as '13', '5
//51', '1351315'. 
//
// Return the number of positive integers that can be written (using the digits 
//of D) that are less than or equal to N. 
//
// 
//
// Example 1: 
//
// 
//Input: D = ["1","3","5","7"], N = 100
//Output: 20
//Explanation: 
//The 20 numbers that can be written are:
//1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
// 
//
// 
// Example 2: 
//
// 
//Input: D = ["1","4","9"], N = 1000000000
//Output: 29523
//Explanation: 
//We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
//
//81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
//2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbe
//rs.
//In total, this is 29523 integers that can be written using the digits of D. 
// 
//
// 
//
// Note: 
//
// 
// D is a subset of digits '1'-'9' in sorted order. 
// 1 <= N <= 10^9 
// 
// Related Topics Math Dynamic Programming


class Solution {
    List<Integer> num;
    int[] dp;
    Set<Integer> hs;

    public int atMostNGivenDigitSet(String[] D, int N) {
        num = new ArrayList<>();
        dp = new int[15];
        hs = new HashSet<>();
        Arrays.fill(dp, -1);
        for (String d:D)
            hs.add(Integer.parseInt(d));

        while (N > 0) {
            num.add(N % 10);
            N /= 10;
        }
        return dfs(num.size(), true, true);
    }

    public int dfs(int pos, boolean limit, boolean lead) {
        if (pos == 0)
            return lead ? 0 : 1;
        if (!lead && !limit && dp[pos] != -1)
            return dp[pos];

        int up = limit ? num.get(pos - 1) : 9;

        int res = 0;
        for (int i = 0; i <= up; i++) {
            if ((lead && i == 0) || hs.contains(i))
                res += dfs(pos - 1, limit && (i == up), lead && (i == 0));
        }

        if (!lead)
            dp[pos] = res;

        return res;
    }
}