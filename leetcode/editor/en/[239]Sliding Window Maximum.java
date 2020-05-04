//Given an array nums, there is a sliding window of size k which is moving from 
//the very left of the array to the very right. You can only see the k numbers in 
//the window. Each time the sliding window moves right by one position. Return the
// max sliding window. 
//
// Follow up: 
//Could you solve it in linear time? 
//
// Example: 
//
// 
//Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//Output: [3,3,5,5,6,7] 
//Explanation: 
//
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics Heap Sliding Window


class Solution {
    public class MonoQueue {
        private Deque<Integer> q;

        MonoQueue() {
            q = new ArrayDeque<>();
        }
        public void push(int num) {
            while (!q.isEmpty() && q.peekLast() < num)
                q.pollLast();
            q.offer(num);
        }
        public int pop() {
            return q.pop();
        }
        public int peek() {
            return q.peek();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        MonoQueue mq = new MonoQueue();

        for (int i = 0; i < n; i++) {
            mq.push(nums[i]);
            if (i >= k - 1) {
                res[i - k + 1] = mq.peek();
                if (nums[i - k + 1] == mq.peek())
                    mq.pop();
            }
        }

        return res;
    }
}