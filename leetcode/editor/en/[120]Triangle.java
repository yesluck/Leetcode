//Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below. 
//
// For example, given the following triangle 
//
// 
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
//
// Note: 
//
// Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle. 
//

// Backtracking (TLE!)
class Solution {
    int min;
    int n;

    public int minimumTotal(List<List<Integer>> triangle) {
        min = Integer.MAX_VALUE;
        n = triangle.size();
        helper(triangle, 0, 0, 0);
        return min;
    }

    public void helper(List<List<Integer>> triangle, int level, int pos, int sum) {
        if (level == n) {
            if (sum < min)
                min = sum;
            return;
        }

        sum += triangle.get(level).get(pos);
        helper(triangle, level + 1, pos, sum);
        helper(triangle, level + 1, pos + 1, sum);
        sum -= triangle.get(level).get(pos);
    }
}


// Bottom-up DP
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++)
            dp[i] = triangle.get(n - 1).get(i);

        for (int i = n - 2; i >= 0; i--)
            for (int j = 0; j <= i; j++)
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);

        return dp[0];
    }
}

// In-place
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        for (int i = n - 2; i >= 0; i--)
            for (int j = 0; j <= i; j++)
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));

        return triangle.get(0).get(0);
    }
}