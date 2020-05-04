//There are 8 prison cells in a row, and each cell is either occupied or vacant.
// 
//
// Each day, whether the cell is occupied or vacant changes according to the fol
//lowing rules: 
//
// 
// If a cell has two adjacent neighbors that are both occupied or both vacant, t
//hen the cell becomes occupied. 
// Otherwise, it becomes vacant. 
// 
//
// (Note that because the prison is a row, the first and the last cells in the r
//ow can't have two adjacent neighbors.) 
//
// We describe the current state of the prison in the following way: cells[i] ==
// 1 if the i-th cell is occupied, else cells[i] == 0. 
//
// Given the initial state of the prison, return the state of the prison after N
// days (and N such changes described above.) 
//
// 
//
// 
// 
// 
// 
//
// 
// Example 1: 
//
// 
//Input: cells = [0,1,0,1,1,0,0,1], N = 7
//Output: [0,0,1,1,0,0,0,0]
//Explanation: 
//The following table summarizes the state of the prison on each day:
//Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
//Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
//Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
//Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
//Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
//Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
//Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
//Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
//
// 
//
// 
// Example 2: 
//
// 
//Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
//Output: [0,0,1,1,1,1,1,0]
// 
//
// 
//
// Note: 
//
// 
// cells.length == 8 
// cells[i] is in {0, 1} 
// 1 <= N <= 10^9 
// 
// 
// 
// Related Topics Hash Table


class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        int n = cells.length;
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            int[] temp = new int[n];
            int state = 0;

            for (int j = 1; j < n - 1; j++) {
                temp[j] = ((cells[j - 1] ^ cells[j + 1]) == 0 ? 1 : 0);
                state |= (temp[j] << j);
            }

            if (hm.containsKey(state)) {
                int period = i - hm.get(state);
                return prisonAfterNDays(cells, N % period);
            }

            hm.put(state, i);
            cells = temp;
        }
        return cells;
    }
}