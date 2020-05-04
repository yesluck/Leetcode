//Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram. 
//
// 
//
// 
//Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3]. 
//
// 
//
// 
//The largest rectangle is shown in the shaded area, which has area = 10 unit. 
//
// 
//
// Example: 
//
// 
//Input: [2,1,5,6,2,3]
//Output: 10
// 
//

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
}