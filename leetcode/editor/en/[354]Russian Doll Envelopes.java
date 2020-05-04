//You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope. 
//
// What is the maximum number of envelopes can you Russian doll? (put one inside other) 
//
// Note: 
//Rotation is not allowed. 
//
// Example: 
//
// 
// 
//Input: [[5,4],[6,4],[6,7],[2,3]]
//Output: 3 
//Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
// 
// 
//


// Using O(n2) DP in 300: 136ms
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];   // Note: decreasing in second dimension to avoid being fit
                else
                    return arr1[0] - arr2[0];
            }
        });

        int n = envelopes.length;
        int[] secondDim = new int[n];

        for (int i = 0; i < n; i++)
            secondDim[i] = envelopes[i][1];
        return lengthOfLIS(secondDim);
    }

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


// Using O(nlogn) DP in 300: 12ms
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];   // Note: decreasing in second dimension to avoid being fit
                else
                    return arr1[0] - arr2[0];
            }
        });

        int n = envelopes.length;
        int[] secondDim = new int[n];

        for (int i = 0; i < n; i++)
            secondDim[i] = envelopes[i][1];
        return lengthOfLIS(secondDim);
    }

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
