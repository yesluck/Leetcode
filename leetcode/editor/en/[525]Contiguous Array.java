//Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1. 
//
//
// Example 1: 
// 
//Input: [0,1]
//Output: 2
//Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
// 
// 
//
// Example 2: 
// 
//Input: [0,1,0]
//Output: 2
//Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
// 
// 
//
// Note:
//The length of the given binary array will not exceed 50,000.
//

// Original DP O(KN2)
class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> hm = new HashMap<>();
        int temp = 0, res = 0;

        hm.put(0, -1);
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0)
                temp -= 1;
            else
                temp += 1;
            if (hm.containsKey(temp))
                res = Math.max(res, i - hm.get(temp));
            else
                hm.put(temp, i);
        }

        return res;
    }
}


// DP + Binary Search + memo O(KNlogN)
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
            int left = 1, right = N + 1;    // right = N 也行，但是这边我还是试图保持左闭右开
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


// 逆向DP O(KN)
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