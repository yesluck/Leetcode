//A message containing letters from A-Z is being encoded to numbers using the following mapping: 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// Given a non-empty string containing only digits, determine the total number of ways to decode it. 
//
// Example 1: 
//
// 
//Input: "12"
//Output: 2
//Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// 
//
// Example 2: 
//
// 
//Input: "226"
//Output: 3
//Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6). 
//


// recursive with memo
class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings(String s) {
        if (s.length() == 0)
            return 0;
        return recursiveWithMemo(0, s);
    }

    public int recursiveWithMemo(int pos, String s) {
        int n = s.length();
        if (pos == n)
            return 1;

        if (s.charAt(pos) == '0')
            return 0;

        if (pos == n - 1)
            return 1;

        if (memo.containsKey(pos))
            return memo.get(pos);

        int res = recursiveWithMemo(pos + 1, s);
        if (Integer.parseInt(s.substring(pos, pos + 2)) <= 26)
            res += recursiveWithMemo(pos + 2, s);

        memo.put(pos, res);

        return res;
    }
}

// dp
class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0)
            return 0;
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i < n + 1; i++) {
            if (i >= 2 && ((s.charAt(i - 2) == '1') || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')))
                dp[i] += dp[i - 2];
            if (i >= 1 && s.charAt(i - 1) != '0')
                dp[i] += dp[i - 1];
        }
        return dp[n];
    }
}