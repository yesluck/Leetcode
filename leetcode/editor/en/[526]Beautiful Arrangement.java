//Suppose you have N integers from 1 to N. We define a beautiful arrangement as 
//an array that is constructed by these N numbers successfully if one of the follo
//wing is true for the ith position (1 <= i <= N) in this array: 
//
// 
// The number at the ith position is divisible by i. 
// i is divisible by the number at the ith position. 
// 
//
// 
//
// Now given N, how many beautiful arrangements can you construct? 
//
// Example 1: 
//
// 
//Input: 2
//Output: 2
//Explanation: 
//
//The first beautiful arrangement is [1, 2]:
//
//Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
//
//Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
//
//The second beautiful arrangement is [2, 1]:
//
//Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
//
//Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
// 
//
// 
//
// Note: 
//
// 
// N is a positive integer and will not exceed 15. 
// 
//
// 
// Related Topics Backtracking


class Solution {
    int res;
    boolean[] visited;

    public int countArrangement(int N) {
        res = 0;
        visited = new boolean[N + 1];
        backtracking(N, 1);
        return res;
    }

    public void backtracking(int n, int pos) {
        if (pos > n) {
            res += 1;
            return;
        }
        for (int i = 1; i < n + 1; i++) {
            if (visited[i] || (i % pos != 0 && pos % i != 0))
                continue;
            visited[i] = true;
            backtracking(n, pos + 1);
            visited[i] = false;
        }
    }
}
