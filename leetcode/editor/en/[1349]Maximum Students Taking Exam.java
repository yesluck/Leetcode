//Given a m * n matrix seats that represent seats distributions in a classroom. 
//If a seat is broken, it is denoted by '#' character otherwise it is denoted by a
// '.' character. 
//
// Students can see the answers of those sitting next to the left, right, upper 
//left and upper right, but he cannot see the answers of the student sitting direc
//tly in front or behind him. Return the maximum number of students that can take 
//the exam together without any cheating being possible.. 
//
// Students must be placed in seats in good condition. 
//
// 
// Example 1: 
//
// 
//Input: seats = [["#",".","#","#",".","#"],
//                [".","#","#","#","#","."],
//                ["#",".","#","#",".","#"]]
//Output: 4
//Explanation: Teacher can place 4 students in available seats so they don't che
//at on the exam. 
// 
//
// Example 2: 
//
// 
//Input: seats = [[".","#"],
//                ["#","#"],
//                ["#","."],
//                ["#","#"],
//                [".","#"]]
//Output: 3
//Explanation: Place all students in available seats. 
//
// 
//
// Example 3: 
//
// 
//Input: seats = [["#",".",".",".","#"],
//                [".","#",".","#","."],
//                [".",".","#",".","."],
//                [".","#",".","#","."],
//                ["#",".",".",".","#"]]
//Output: 10
//Explanation: Place students in available seats in column 1, 3 and 5.
// 
//
// 
// Constraints: 
//
// 
// seats contains only characters '.' and'#'. 
// m == seats.length 
// n == seats[i].length 
// 1 <= m <= 8 
// 1 <= n <= 8 
// 
// Related Topics Dynamic Programming


class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int[] validRows = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                validRows[i] = (validRows[i] << 1) + (seats[i][j] == '.' ? 1 : 0);
            }
        }

        int stateSize = 1 << n;
        int[][] dp = new int[m][stateSize];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int state = 0; state < stateSize; state++) {
                // state & validRows[i] == state: state is subset of validRows[i]
                // state & (state >> 1) == 0: no adjancent students in this state
                if (((state & validRows[i]) == state) && ((state & (state >> 1)) == 0)) {
                    if (i == 0)
                        dp[i][state] = Integer.bitCount(state);
                    else {
                        for (int state0 = 0; state0 < stateSize; state0++) {
                            // state & (state0 >> 1) == 0: for every student in state, no student on upper left
                            // (state >> 1) & state0 == 0: for every student in state, no student on upper right
                            // dp[i - 1][state0] != -1: state0 is valid
                            if (((state & (state0 >> 1)) == 0) && (((state >> 1) & state0) == 0) && dp[i - 1][state0] != -1)
                                dp[i][state] = Math.max(dp[i][state], dp[i - 1][state0] + Integer.bitCount(state));
                        }
                    }
                    res = Math.max(res, dp[i][state]);
                }
            }
        }

        return res;
    }
}