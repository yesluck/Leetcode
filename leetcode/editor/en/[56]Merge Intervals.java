//Given a collection of intervals, merge all overlapping intervals. 
//
// Example 1: 
//
// 
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping. 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature. 
//

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if(n == 0)  return intervals;
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        int[] temp = intervals[0];  // previous interval. !!! arrays pass pointer !!!
        res.add(temp);
        for(int i = 1; i < n; i++) {
            int[] interval = intervals[i];
            if(interval[0] <= temp[1]) {
                // overlap
                temp[1] = Math.max(interval[1], temp[1]);
            } else {
                // no overlap -> add previous interval to result
                temp = interval;
                res.add(temp);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}