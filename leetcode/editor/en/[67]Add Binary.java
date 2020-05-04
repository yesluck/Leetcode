//Given two binary strings, return their sum (also a binary string). 
//
// The input strings are both non-empty and contains only characters 1 or 0. 
//
// Example 1: 
//
// 
//Input: a = "11", b = "1"
//Output: "100" 
//
// Example 2: 
//
// 
//Input: a = "1010", b = "1011"
//Output: "10101" 
//

class Solution {
    public String addBinary(String a, String b) {
        int len_a = a.length();
        int len_b = b.length();
        int c = 0;
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < Math.max(len_a, len_b); i++)
        {
            int tempA = len_a > i ? a.charAt(len_a - i - 1) - '0' : 0;
            int tempB = len_b > i ? b.charAt(len_b - i - 1) - '0' : 0;
            res.insert(0, (tempA + tempB + c) % 2);
            c = tempA + tempB + c > 1 ? 1 : 0;
        }
        if(c == 1)  res.insert(0, 1);
        return res.toString();
    }
}


import java.math.BigInteger;

class Solution2 {
    public String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2), y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }
}