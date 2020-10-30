//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, determine if s can be segmented into a space-separated sequence of 
//one or more dictionary words. 
//
// Note: 
//
// 
// The same word in the dictionary may be reused multiple times in the segmentat
//ion. 
// You may assume the dictionary does not contain duplicate words. 
// 
//
// Example 1: 
//
// 
//Input: s = "leetcode", wordDict = ["leet", "code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".
// 
//
// Example 2: 
//
// 
//Input: s = "applepenapple", wordDict = ["apple", "pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple pe
//n apple".
//             Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output: false
// 
// Related Topics Dynamic Programming

// Backtracking
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return helper(s, new HashSet(wordDict), 0);
    }

    private boolean helper(String s, Set<String> dict, int start) {
        int n = s.length();

        if (start == n)
            return true;

        for (int i = start; i <= n; i++) {
            if (dict.contains(s.substring(start, i)) && helper(s, dict, i))
                return true;
        }

        return false;
    }
}

// Backtracking + memo
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return helper(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean helper(String s, Set<String> dict, int start, Boolean[] memo) {
        int n = s.length();

        if (start == n)
            return true;

        if (memo[start] != null)
            return memo[start];

        for (int i = start; i <= n; i++) {
            if (dict.contains(s.substring(start, i)) && helper(s, dict, i, memo)) {
                memo[start] = true;
                return true;
            }
        }

        memo[start] = false;
        return false;
    }
}

// BFS
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> dict = new HashSet(wordDict);

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);

        while (!q.isEmpty()) {
            int start = q.poll();

            if (!visited[start]) {
                for (int i = start + 1; i <= n; i++) {
                    if (dict.contains(s.substring(start, i))) {
                        q.offer(i);
                        if (i == n)
                            return true;
                    }
                }

                visited[start] = true;
            }
        }

        return false;
    }
}

// DP
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> dict = new HashSet(wordDict);

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
