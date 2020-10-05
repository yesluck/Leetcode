//Given an array of integers nums and an integer limit, return the size of the l
//ongest continuous subarray such that the absolute difference between any two ele
//ments is less than or equal to limit. 
//
// In case there is no subarray satisfying the given condition return 0. 
//
// 
// Example 1: 
//
// 
//Input: nums = [8,2,4,7], limit = 4
//Output: 2 
//Explanation: All subarrays are: 
//[8] with maximum absolute diff |8-8| = 0 <= 4.
//[8,2] with maximum absolute diff |8-2| = 6 > 4. 
//[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//[2] with maximum absolute diff |2-2| = 0 <= 4.
//[2,4] with maximum absolute diff |2-4| = 2 <= 4.
//[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//[4] with maximum absolute diff |4-4| = 0 <= 4.
//[4,7] with maximum absolute diff |4-7| = 3 <= 4.
//[7] with maximum absolute diff |7-7| = 0 <= 4. 
//Therefore, the size of the longest subarray is 2.
// 
//
// Example 2: 
//
// 
//Input: nums = [10,1,2,4,7,2], limit = 5
//Output: 4 
//Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute 
//diff is |2-7| = 5 <= 5.
// 
//
// Example 3: 
//
// 
//Input: nums = [4,2,2,2,4,4,2,2], limit = 0
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// 1 <= nums[i] <= 10^9 
// 0 <= limit <= 10^9 
// 
// Related Topics Array Sliding Window


// My answer
// In fact it's wrong and it will TLE now!!!
// DON'T LOOK AT IT AGAIN!!!
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int res = 1;

        for (int i = 0; i < n; i++) {
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                if (max - min > limit)
                    break;
                res = Math.max(res, j - i + 1);
                if (res == n)
                    return res;
            }
        }

        return res;
    }
}


// Mono Queue
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        Deque<Integer> minQueue = new ArrayDeque<>();   // head is minimum, increasing to tail
        Deque<Integer> maxQueue = new ArrayDeque<>();   // head is maximum, decreasing to tail
        int res = 0;

        int left = 0;
        for (int right = 0; right < n; right++) {
            int num = nums[right];
            while (!minQueue.isEmpty() && minQueue.peekLast() > num)
                minQueue.pollLast();
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < num)
                maxQueue.pollLast();
            minQueue.offer(num);
            maxQueue.offer(num);

            // move left index to right while already larger than limit
            while (maxQueue.peek() - minQueue.peek() > limit) {
                // pop out the left item (similar to sliding window in 239)
                if (nums[left] == minQueue.peek())
                    minQueue.pop();
                if (nums[left] == maxQueue.peek())
                    maxQueue.pop();
                left++;
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
