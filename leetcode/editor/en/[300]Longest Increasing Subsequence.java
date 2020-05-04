//Given an unsorted array of integers, find the length of longest increasing subsequence. 
//
// Example: 
//
// 
//Input: [10,9,2,5,3,7,101,18]
//Output: 4 
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
//
// Note: 
//
// 
// There may be more than one LIS combination, it is only necessary for you to return the length. 
// Your algorithm should run in O(n2) complexity. 
// 
//
// Follow up: Could you improve it to O(n log n) time complexity? 
//


// O(n2) DP
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        int[] dp = new int[n];
        int res = 1;

        for (int i = 0; i < n; i++) {
            int maxdp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    maxdp = Math.max(maxdp, dp[j]);
            }
            dp[i] = 1 + maxdp;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}


// O(nlogn) DP
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        List<Integer> tails = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int size = tails.size();
            if (size == 0 || nums[i] > tails.get(size - 1))
                tails.add(nums[i]);
            else {
                int idx = CeilBinarySearch(tails, nums[i]);
                if (idx == 0 || nums[i] > tails.get(idx - 1))
                    tails.set(idx, nums[i]);
            }
        }
        return tails.size();
    }

    public int CeilBinarySearch(List<Integer> nums, int num) {
        int n = nums.size();
        int left = 0, right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) > num)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}



