//A conveyor belt has packages that must be shipped from one port to another wit
//hin days days. 
//
// The ith package on the conveyor belt has a weight of weights[i]. Each day, we
// load the ship with packages on the conveyor belt (in the order given by weights
//). We may not load more weight than the maximum weight capacity of the ship. 
//
// Return the least weight capacity of the ship that will result in all the pack
//ages on the conveyor belt being shipped within days days. 
//
// 
// Example 1: 
//
// 
//Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
//Output: 15
//Explanation: A ship capacity of 15 is the minimum to ship all the packages in 
//5 days like this:
//1st day: 1, 2, 3, 4, 5
//2nd day: 6, 7
//3rd day: 8
//4th day: 9
//5th day: 10
//
//Note that the cargo must be shipped in the order given, so using a ship of cap
//acity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8)
//, (9), (10) is not allowed.
// 
//
// Example 2: 
//
// 
//Input: weights = [3,2,2,4,1,4], days = 3
//Output: 6
//Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3
// days like this:
//1st day: 3, 2
//2nd day: 2, 4
//3rd day: 1, 4
// 
//
// Example 3: 
//
// 
//Input: weights = [1,2,3,1,1], days = 4
//Output: 3
//Explanation:
//1st day: 1
//2nd day: 2
//3rd day: 3
//4th day: 1, 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= days <= weights.length <= 5 * 104 
// 1 <= weights[i] <= 500 
// 
// Related Topics Array Binary Search


class Solution {
    private int getSum(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum;
    }

    private int getMax(int[] nums) {
        int max = 0;
        for (int num : nums)
            max = Math.max(max, num);
        return max;
    }

    private boolean canFinish(int[] weights, int days, int capacity) {
        int i = 0;
        for (int day = 0; day < days; day++) {
            int maxCap = capacity;
            while (maxCap - weights[i] >= 0) {
                maxCap -= weights[i];
                i++;
                if (i == weights.length)
                    return true;
            }
        }
        return false;
    }

    public int shipWithinDays(int[] weights, int days) {
        int left = getMax(weights), right = getSum(weights) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean canFinishWithMidCap = canFinish(weights, days, mid);
            if (canFinishWithMidCap) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
