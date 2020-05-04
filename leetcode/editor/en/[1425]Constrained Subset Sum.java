//Given an integer array nums and an integer k, return the maximum sum of a non-
//empty subset of that array such that for every two consecutive integers in the s
//ubset, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied. 
//
//
// A subset of an array is obtained by deleting some number of elements (can be 
//zero) from the array, leaving the remaining elements in their original order. 
//
// 
// Example 1: 
//
// 
//Input: nums = [10,2,-10,5,20], k = 2
//Output: 37
//Explanation: The subset is [10, 2, 5, 20].
// 
//
// Example 2: 
//
// 
//Input: nums = [-1,-2,-3], k = 1
//Output: -1
//Explanation: The subset must be non-empty, so we choose the largest number.
// 
//
// Example 3: 
//
// 
//Input: nums = [10,-2,-10,-5,20], k = 2
//Output: 23
//Explanation: The subset is [10, -2, -5, 20].
// 
//
// 
// Constraints: 
//
// 
// 1 <= k <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 
// Related Topics Dynamic Programming


// answer during contest [TLE]
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int res = -100000;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int maxdp = 0;
            for (int j = Math.max(0, i - k); j < i; j++) {
                maxdp = Math.max(maxdp, dp[j]);
            }
            dp[i] = nums[i] + maxdp;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}


// Mono Queue ~239. Sliding window maximum
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
        public boolean isEmpty() {
            return q.isEmpty();
        }
    }

    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        MonoQueue mq = new MonoQueue();

        int res = nums[0];

        for (int i = 0; i < n; i++) {
            int maxInWindow;
            dp[i] = nums[i] + ((!mq.isEmpty() && mq.peek() > 0) ? mq.peek() : 0);
            res = Math.max(res, dp[i]);
            mq.push(dp[i]);
            if (i >= k) {
                maxInWindow = mq.peek();
                if (dp[i - k] == mq.peek())
                    mq.pop();
            }
        }
        return res;
    }
}