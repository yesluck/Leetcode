//There are N children standing in a line. Each child is assigned a rating value
//. 
//
// You are giving candies to these children subjected to the following requireme
//nts: 
//
// 
// Each child must have at least one candy. 
// Children with a higher rating get more candies than their neighbors. 
// 
//
// What is the minimum candies you must give? 
//
// Example 1: 
//
// 
//Input: [1,0,2]
//Output: 5
//Explanation: You can allocate to the first, second and third child with 2, 1, 
//2 candies respectively.
// 
//
// Example 2: 
//
// 
//Input: [1,2,2]
//Output: 4
//Explanation: You can allocate to the first, second and third child with 1, 2, 
//1 candies respectively.
//             The third child gets 1 candy because it satisfies the above two c
//onditions.
// 
// Related Topics Greedy


// Use two arrays
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int res = 0;

        int[] left2right = new int[n];
        int[] right2left = new int[n];

        left2right[0] = 1;
        right2left[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            left2right[i] = ratings[i] > ratings[i - 1] ? left2right[i - 1] + 1 : 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            right2left[i] = ratings[i] > ratings[i + 1] ? right2left[i + 1] + 1 : 1;
        }

        for (int i = 0; i < n; i++) {
            res += Math.max(left2right[i], right2left[i]);
        }

        return res;
    }
}

// Use one array
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int res = 0;

        int[] candies = new int[n];

        candies[0] = 1;

        for (int i = 1; i < n; i++) {
            candies[i] = ratings[i] > ratings[i - 1] ? candies[i - 1] + 1 : 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            candies[i] = ratings[i] > ratings[i + 1] ? Math.max(candies[i + 1] + 1, candies[i]) : candies[i];
        }

        for (int i = 0; i < n; i++) {
            res += candies[i];
        }

        return res;
    }
}
