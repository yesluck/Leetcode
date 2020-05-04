//Given an array of non-negative integers, you are initially positioned at the first index of the array. 
//
// Each element in the array represents your maximum jump length at that position. 
//
// Determine if you are able to reach the last index. 
//
// Example 1: 
//
// 
//Input: [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum
//             jump length is 0, which makes it impossible to reach the last index.
// 
//

// backtracking TLE
class Solution {
    private boolean canJumpFromPosition(int pos, int[] nums) {
        if(pos == nums.length - 1)  return true;
        int furthestJump = Math.min(pos + nums[pos], nums.length - 1);
        for(int i = pos + 1; i <= furthestJump; i++) {
            if(canJumpFromPosition(i, nums)) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }
}

// dp
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] status = new boolean[n];
        status[n - 1] = true;
        for(int i = n - 2; i >= 0; i--) {
            int furthest = Math.min(n - 1, i + nums[i]);
            for(int j = furthest; j > i; j--)
                if(status[j]) {
                    status[i] = true;
                    break;
                }
        }
        return status[0];
    }
}

// greedy
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int last = n - 1;
        for(int i = n - 2; i >= 0; i--) {
            if(i + nums[i] >= last) {
                last = i;
            }
        }
        return last == 0;
    }
}
