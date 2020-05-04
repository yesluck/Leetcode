//
//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
// 
//
// 
//For example, given n = 3, a solution set is:
// 
// 
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
//

// Backtracking approach
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res, "", 0, 0, n);
        return res;
    }
    private void backtracking(List<String> res, String temp, int open, int close, int n) {
        if(temp.length() == 2*n) {
            res.add(temp);
            return;
        }
        if(open < n)    backtracking(res, temp + "(", open + 1, close, n);
        if(close < open)    backtracking(res, temp + ")", open, close + 1, n);
    }
}