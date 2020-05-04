//Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area. 
//
// Example: 
//
// 
//Input:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//Output: 6
// 
//


class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j-1] + 1;
                    int width = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        res = Math.max(res, width * (i - k + 1));
                    }
                }
            }
        }
        return res;
    }
}


class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;

        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()])
                // decreasing -> pop out larger items, push in new item
                res = Math.max(res, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }

        while (stack.peek() != -1)
            res = Math.max(res, heights[stack.pop()] * (n - stack.peek() - 1));

        return res;
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        int res = 0;
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    dp[j] = dp[j] + 1;
                else
                    dp[j] = 0;
            }
            res = Math.max(res, largestRectangleArea(dp));
        }
        return res;
    }
}


class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        int res = 0;
        int[] left = new int[n], right = new int[n], height = new int[n];
        Arrays.fill(right, n);

        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n - 1;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    height[j] += 1;
                else
                    height[j] = 0;
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    left[j] = Math.max(left[j], cur_left);
                else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1')
                    right[j] = Math.min(right[j], cur_right);
                else {
                    right[j] = n - 1;
                    cur_right = j - 1;
                }
            }
            for (int j = 0; j < n; j++)
                res = Math.max(res, (right[j] - left[j] + 1) * height[j]);
        }
        return res;
    }
}
