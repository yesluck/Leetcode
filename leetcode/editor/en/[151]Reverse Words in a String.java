//Given an input string s, reverse the order of the words. 
//
// A word is defined as a sequence of non-space characters. The words in s will 
//be separated by at least one space. 
//
// Return a string of the words in reverse order concatenated by a single space.
// 
//
// Note that s may contain leading or trailing spaces or multiple spaces between
// two words. The returned string should only have a single space separating the w
//ords. Do not include any extra spaces. 
//
// 
// Example 1: 
//
// 
//Input: s = "the sky is blue"
//Output: "blue is sky the"
// 
//
// Example 2: 
//
// 
//Input: s = "  hello world  "
//Output: "world hello"
//Explanation: Your reversed string should not contain leading or trailing space
//s.
// 
//
// Example 3: 
//
// 
//Input: s = "a good   example"
//Output: "example good a"
//Explanation: You need to reduce multiple spaces between two words to a single 
//space in the reversed string.
// 
//
// Example 4: 
//
// 
//Input: s = "  Bob    Loves  Alice   "
//Output: "Alice Loves Bob"
// 
//
// Example 5: 
//
// 
//Input: s = "Alice does not even like bob"
//Output: "bob like even not does Alice"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 104 
// s contains English letters (upper-case and lower-case), digits, and spaces ' 
//'. 
// There is at least one word in s. 
// 
//
// 
//
// Follow up: 
//
// 
// Could you solve it in-place with O(1) extra space? 
// 
//
// 
// Related Topics String


// Use built-in functions
class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}


// Not using built-in functions
class Solution {
    public String reverseWords(String s) {
        // 1. trim
        StringBuilder sb = trimSpaces(s);
        // 2. reverse entire string
        reverseString(sb);
        // 3. reverse each word
        reverseEach(sb);

        return sb.toString();
    }

    private StringBuilder trimSpaces(String s) {
        int n = s.length();
        int left = 0, right = n - 1;

        while (left <= right && s.charAt(left) == ' ')
            left++;
        while (left <= right && s.charAt(right) == ' ')
            right--;

        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ')
                sb.append(c);
            left++;
        }

        return sb;
    }

    private void reverseString(StringBuilder sb) {
        int n = sb.length();
        int left = 0, right = n - 1;

        while(left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, temp);
        }
    }

    private void reverseEach(StringBuilder sb) {
        int n = sb.length();
        int left = 0, right = 0;

        while (left < n) {
            while (right < n && sb.charAt(right) != ' ')
                right++;
            reverse(sb, left, right - 1);
            right += 1;
            left = right;
        }
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, temp);
        }
    }
}


// Two passes deque, TODO
