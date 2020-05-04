//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000. 
//
// Example 1: 
//
// 
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: "cbbd"
//Output: "bb"
// 
//

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n < 2)   return s;
        boolean[][] dp = new boolean[n][n];
        int left = 0, right = 0;
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < i; j++) {
                dp[j][i] = s.charAt(i) == s.charAt(j) && (i-j < 2 || dp[j+1][i-1]);
                if(dp[j][i] && i-j > right-left) {
                    right = i;
                    left = j;
                }
            }
            dp[i][i] = true;       // don't forget
        }
        return s.substring(left, right+1);
    }
}

class Solution2 {
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2) return s;
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i++) {
            int len1 = extendFromCenter(s, i, i);
            int len2 = extendFromCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > end - start) {
                start = i - len / 2;
                end = i + (len+1) / 2;
            }
        }
        return s.substring(start, end+1);
    }
    public int extendFromCenter(String s, int c1, int c2) {
        int left = c1, right = c2;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }   // this will cause left expand 1 more, right expand 1 more. So: the actual length should be
        // (right-1) - (left+1) + 1 = right - left - 1 !!! We return right - left - 2 for better understanding
        return right - left - 2;
    }
}