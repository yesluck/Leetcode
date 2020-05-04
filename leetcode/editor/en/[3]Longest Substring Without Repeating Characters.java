//Given a string, find the length of the longest substring without repeating characters. 
//
// 
// Example 1: 
//
// 
//Input: "abcabcbb"
//Output: 3 
//Explanation: The answer is "abc", with the length of 3. 
// 
//
// 
// Example 2: 
//
// 
//Input: "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// 
// Example 3: 
//
// 
//Input: "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3. 
//             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
// 
// 
// 
// 
//

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        int res = 0;
        for(int i = 0, j = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(hm.containsKey(c)) {
                j = Math.max(j, hm.get(c) + 1);
            }
            hm.put(c, i);
            res = Math.max(res, i - j + 1); // remember to use this outside the loop!
        }
        return res;
    }
}