//Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring. 
//
// Example 1: 
//
// 
//Input: "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()"
// 
//
// Example 2: 
//
// 
//Input: ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()"
// 
//

// dp solution
class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        int n = s.length();
        int[] dp = new int[n];
        for(int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if(c == '(')    continue;
            if(s.charAt(i) == '(') {
                dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
            }
            else {
                if(i - dp[i-1] > 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    dp[i] = (i - dp[i-1] - 2 >= 0 ? dp[i - dp[i-1] - 2] : 0) + dp[i-1] + 2;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

// stack solution
class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(i);
            }
            else {
                stack.pop();
                if(stack.isEmpty()) stack.push(i);
                else    res = Math.max(res, i - stack.peek());
            }
        }
        return res;
    }
}

// two pass with O(1) space
class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        int left = 0, right = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(')    left += 1;
            else    right += 1;
            if(left == right)   res = Math.max(res, left + right);
            if(left < right) {
                left = 0;
                right = 0;
            }
        }
        left = 0; right = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == '(')    left += 1;
            else    right += 1;
            if(left == right)   res = Math.max(res, left + right);
            if(left > right) {
                left = 0;
                right = 0;
            }
        }
        return res;
    }
}