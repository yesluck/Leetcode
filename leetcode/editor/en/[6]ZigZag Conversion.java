//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility) 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R
// 
//
// And then read line by line: "PAHNAPLSIIGYIR" 
//
// Write the code that will take a string and make this conversion given a number of rows: 
//
// 
//string convert(string s, int numRows); 
//
// Example 1: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
// 
//
// Example 2: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//
//P     I    N
//A   L S  I G
//Y A   H R
//P     I 
//

class Solution {
    public String convert(String s, int numRows) {
        StringBuffer[] sbs = new StringBuffer[numRows];
        for(int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuffer();
        }
        int idx = 0;
        while(idx < s.length()) {
            for(int i = 0; i < numRows && idx < s.length(); i++) {
                sbs[i].append(s.charAt(idx));
                idx++;
            }
            for(int i = numRows-2; i > 0 && idx < s.length(); i--) {
                sbs[i].append(s.charAt(idx));
                idx++;
            }
        }
        for(int i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }
}