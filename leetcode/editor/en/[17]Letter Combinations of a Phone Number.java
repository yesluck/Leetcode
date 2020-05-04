//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. 
//
// A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters. 
//
// 
//
// Example: 
//
// 
//Input: "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// Note: 
//
// Although the above answer is in lexicographical order, your answer could be in any order you want. 
//

class Solution {
    public List<String> letterCombinations(String digits) {
        String[] table = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        String temp = "";
        if(digits.length() == 0)    return res;
        backtracking(res, temp, 0, digits, table);
        return res;
    }
    private void backtracking(List<String> res, String temp, int pos, String digits, String[] table) {
        if(temp.length() == digits.length()) {
            res.add(temp);
            return;
        }
        String options = table[digits.charAt(pos) - '0'];
        for(int i = 0; i < options.length(); i++) {
            temp += options.charAt(i);
            backtracking(res, temp, pos+1, digits, table);
            temp = temp.substring(0, pos);
        }
    }
}