//You are given an n x n 2D matrix representing an image. 
//
// Rotate the image by 90 degrees (clockwise). 
//
// Note: 
//
// You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation. 
//
// Example 1: 
//
// 
//Given input matrix = 
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//rotate the input matrix in-place such that it becomes:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
// 
//
// Example 2: 
//
// 
//Given input matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//], 
//
//rotate the input matrix in-place such that it becomes:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
// 
//

/**
 * 3 - 1
 * 4 - 2
 * 5 - 2
 *
 *   0 1 2 3 4
 * 0 v v v v b
 * 1 m       b
 * 2 m       b
 * 3 m       b
 * 4 m n n n n
 *
 * v
 * i,j -> j,n-i-1
 * 0,0 -> 0,4
 * 0,1 -> 1,4
 * 0,2 -> 2,4
 * 0,3 -> 3,4
 *
 * b
 * j,n-i-1 n-i-1,n-j-1
 * 0,4 -> 4,4n
 * 1,4 -> 4,3
 * 2,4 -> 4,2
 * 3,4 -> 4,1
 *
 * n
 * n-i-1,n-j-1,n-j-1,i
 * 4,4 -> 4,0
 * 4,3 -> 3,0
 * 4,2 -> 2,0
 * 4,1 -> 1,0
 *
 * m
 * n-j-1,i,i,j
 * 4,0 -> 0,0
 * 3,0 -> 0,1
 * 2,0 -> 0,2
 * 1,0 -> 0,3
 */

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n < 2)  return;
        int numIter = n / 2;
        for(int i = 0; i < numIter; i++) {
            for(int j = i; j < n-i-1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }
}