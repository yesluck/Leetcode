//Say you have an array for which the ith element is the price of a given stock on day i. 
//
// Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions: 
//
// 
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again). 
// After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day) 
// 
//
// Example: 
//
// 
//Input: [1,2,3,0,2]
//Output: 3 
//Explanation: transactions = [buy, sell, cooldown, buy, sell]
//

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        int[] s0 = new int[n], s1 = new int[n], s2 = new int[n];

        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);
            s2[i] = s1[i - 1] + prices[i];
        }

        return Math.max(s0[n - 1], s2[n - 1]);
    }
}


// compress to O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        int s0 = 0, s1 = -prices[0], s2 = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            int s0Temp = s0, s1Temp = s1;
            s0 = Math.max(s0, s2);
            s1 = Math.max(s1, s0Temp - prices[i]);
            s2 = s1Temp + prices[i];
        }

        return Math.max(s0, s2);
    }
}