//Write a function to find the longest common prefix string amongst an array of strings. 
//
// If there is no common prefix, return an empty string "". 
//
// Example 1: 
//
// 
//Input: ["flower","flow","flight"]
//Output: "fl"
// 
//
// Example 2: 
//
// 
//Input: ["dog","racecar","car"]
//Output: ""
//Explanation: There is no common prefix among the input strings.
// 
//
// Note: 
//
// All given inputs are in lowercase letters a-z. 
//

// sort
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)  return "";
        Arrays.sort(strs);
        String first = strs[0], last = strs[strs.length - 1];
        String res = "";
        for(int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if(first.charAt(i) == last.charAt(i)) {
                res += first.charAt(i);
            }
            else    break;
        }
        return res;
    }
}

// devide and conquer
class Solution2 {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        return helper(strs, 0, strs.length - 1);
    }
    private String helper(String[] strs, int left, int right) {
        if(left == right)   return strs[left];
        else {
            int mid = left + (right - left) / 2;
            String lcpLeft = helper(strs, left, mid);
            String lcpRight = helper(strs, mid + 1, right);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }
    private String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for(int i = 0; i < min; i++) {
            if(left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }
}

// binary search (copied)
// I don't think it's good and easy enough
class Solution3 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }
}