//Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string. 
//
// Example 1: 
//
// 
//Input: num1 = "2", num2 = "3"
//Output: "6" 
//
// Example 2: 
//
// 
//Input: num1 = "123", num2 = "456"
//Output: "56088"
// 
//
// Note: 
//
// 
// The length of both num1 and num2 is < 110. 
// Both num1 and num2 contain only digits 0-9. 
// Both num1 and num2 do not contain any leading zero, except the number 0 itself. 
// You must not use any built-in BigInteger library or convert the inputs to integer directly. 
// 
//

class Solution {
    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] pos = new int[n1 + n2];
        for(int i = n1 - 1; i >= 0; i--) {
            for(int j = n2 - 1; j >= 0; j--) {
                int p1 = i + j, p2 = i + j + 1;
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + pos[p2];
                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < n1 + n2; i++) {
            if(res.length() == 0 && pos[i] == 0)    continue;
            res.append(pos[i]);
        }
        return res.length() == 0 ? "0" : res.toString();
    }
}