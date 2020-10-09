//Given a string s, partition s such that every substring of the partition is a 
//palindrome. 
//
// Return all possible palindrome partitioning of s. 
//
// Example: 
//
// 
//Input: "aab"
//Output:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
// 
// Related Topics Backtracking


class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();

        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        helper(res, temp, s, 0);

        return res;
    }

    private void helper(List<List<String>> res, List<String> temp, String s, int pos) {
        int n = s.length();

        if (pos == n) {
            res.add(new ArrayList<String>(temp));
        }

        for (int i = pos; i < n; i++) {
            if (isValid(s, pos, i)) {
                temp.add(s.substring(pos, i + 1));
                helper(res, temp, s, i + 1);
                temp.remove(temp.size() - 1);
            }
        }

    }

    private boolean isValid(String s, int start, int end) {
        if (start > end)
            return true;
        return s.charAt(start) == s.charAt(end) && isValid(s, start + 1, end - 1);
    }
}

