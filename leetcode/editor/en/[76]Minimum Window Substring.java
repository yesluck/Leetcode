//Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n). 
//
// Example: 
//
// 
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"
// 
//
// Note: 
//
// 
// If there is no such window in S that covers all characters in T, return the empty string "". 
// If there is such window, you are guaranteed that there will always be only one unique minimum window in S. 
// 
//

class Solution {
    public String minWindow(String s, String t) {
        int[] buckets = new int[128];
        int m = s.length(), n = t.length();
        int left = 0, right = 0, len = Integer.MAX_VALUE, counter = n;
        String res = "";
        for (int i = 0; i < n; i++)
            buckets[t.charAt(i)] += 1;

        while (right < m) {
            // expand right
            char c1 = s.charAt(right);
            if (buckets[c1] > 0)
                counter -= 1;
            buckets[c1] -= 1;
            right += 1;

            while (counter == 0) {
                // shrink left
                if (right - left < len) {
                    len = right - left;
                    res = s.substring(left, right);
                }
                char c2 = s.charAt(left);
                if (buckets[c2] >= 0)
                    counter += 1;
                buckets[c2] += 1;
                left += 1;
            }
        }

        return res;
    }
}