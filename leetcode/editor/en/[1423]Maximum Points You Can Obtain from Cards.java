//There are several cards arranged in a row, and each card has an associated num
//ber of points The points are given in the integer array cardPoints. 
//
// In one step, you can take one card from the beginning or from the end of the 
//row. You have to take exactly k cards. 
//
// Your score is the sum of the points of the cards you have taken. 
//
// Given the integer array cardPoints and the integer k, return the maximum scor
//e you can obtain. 
//
// 
// Example 1: 
//
// 
//Input: cardPoints = [1,2,3,4,5,6,1], k = 3
//Output: 12
//Explanation: After the first step, your score will always be 1. However, choos
//ing the rightmost card first will maximize your total score. The optimal strateg
//y is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 1
//2.
// 
//
// Example 2: 
//
// 
//Input: cardPoints = [2,2,2], k = 2
//Output: 4
//Explanation: Regardless of which two cards you take, your score will always be
// 4.
// 
//
// Example 3: 
//
// 
//Input: cardPoints = [9,7,7,9,7,7,9], k = 7
//Output: 55
//Explanation: You have to take all the cards. Your score is the sum of points o
//f all cards.
// 
//
// Example 4: 
//
// 
//Input: cardPoints = [1,1000,1], k = 1
//Output: 1
//Explanation: You cannot take the card in the middle. Your best score is 1. 
// 
//
// Example 5: 
//
// 
//Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
//Output: 202
// 
//
// 
// Constraints: 
//
// 
// 1 <= cardPoints.length <= 10^5 
// 1 <= cardPoints[i] <= 10^4 
// 1 <= k <= cardPoints.length 
// 
// Related Topics Array Dynamic Programming Sliding Window


// answer during contest (recursion with memo) [TLE]
class Solution {
    // int[][] dp;
    Map<Integer, Integer> hm;

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // dp = new int[n][n];
        if (k >= n) {
            int res = 0;
            for (int i = 0; i < n; i++)
                res += cardPoints[i];
            return res;
        }
        hm = new HashMap<>();
        return helper(cardPoints, 0, n - 1, 0, k);
    }

    public int helper(int[] cardPoints, int left, int right, int sum, int k) {
        if (k == 0 || left > right)
            return sum;

        if (hm.containsKey(left * 100000 + right))
            return hm.get(left * 100000 + right);

        // if (dp[left][right] > 0)
        //     return dp[left][right];

        int n = cardPoints.length;

        int chooseLeft = helper(cardPoints, left + 1, right, sum + cardPoints[left], k - 1);
        int chooseRight = helper(cardPoints, left, right - 1, sum + cardPoints[right], k - 1);
        hm.put(left * 100000 + right, Math.max(chooseLeft, chooseRight));
        return hm.get(left * 100000 + right);
        // dp[left][right] = Math.max(chooseLeft, chooseRight);
        // return dp[left][right];
    }
}


// Sliding window
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int winSum = 0, sum = 0;

        for (int i = 0; i < Math.min(n - k, n); i++) {
            winSum += cardPoints[i];
            sum += cardPoints[i];
        }

        int minWinSum = winSum;
        for (int i = Math.min(n - k, n); i < n; i++) {
            winSum -= cardPoints[i - (n - k)];
            winSum += cardPoints[i];
            minWinSum = Math.min(minWinSum, winSum);
            sum += cardPoints[i];
        }

        return sum - minWinSum;
    }
}