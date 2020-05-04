//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties: 
//
// 
// Integers in each row are sorted from left to right. 
// The first integer of each row is greater than the last integer of the previous row. 
// 
//
// Example 1: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//Output: true
// 
//
// Example 2: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//Output: false 
//

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;
        if (n== 0)
            return false;

        int left = 0, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int midEle = matrix[mid / n][mid % n];
            if (midEle == target)
                return true;
            if (midEle > target)
                right = mid;
            else
                left = mid + 1;
        }

        return false;
    }
}