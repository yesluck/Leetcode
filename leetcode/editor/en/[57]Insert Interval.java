//Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary). 
//
// You may assume that the intervals were initially sorted according to their start times. 
//
// Example 1: 
//
// 
//Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//Output: [[1,5],[6,9]]
// 
//
// Example 2: 
//
// 
//Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//Output: [[1,2],[3,10],[12,16]]
//Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10]. 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature. 
//

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int newStart = newInterval[0], newEnd = newInterval[1];
        LinkedList<int[]> res = new LinkedList<>();

        int idx = 0;
        while (idx < n && newStart > intervals[idx][0])
            res.add(intervals[idx++]);

        int[] interval = new int[2];
        if (res.isEmpty() || res.getLast()[1] < newStart)
            res.add(newInterval);
        else {
            interval = res.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            res.add(interval);
        }

        while (idx < n) {
            interval = new int[2];
            if (res.getLast()[1] < intervals[idx][0])
                res.add(intervals[idx]);
            else {
                interval = res.removeLast();
                interval[1] = Math.max(interval[1], intervals[idx][1]);
                res.add(interval);
            }
            idx++;
        }

        return res.toArray(new int[res.size()][2]);
    }
}