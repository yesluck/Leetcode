//Given an array of non-negative integers, you are initially positioned at the first index of the array. 
//
// Each element in the array represents your maximum jump length at that position. 
//
// Your goal is to reach the last index in the minimum number of jumps. 
//
// Example: 
//
// 
//Input: [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index. 
//
// Note: 
//
// You can assume that you can always reach the last index. 
//

//这题是一道hard题，解题思想完美落实了“贪心”二字——不需要时我就憋着不放手。
//
//在这里需要设置三个变量：`skip`代表当前跳跃步数，`curMax`代表当前跳跃步数能够reach到的最远距离，
// `nextMax`代表当前实际能够跳到的最远距离（毕竟我有很大潜力，但是你老是不skip，因此我能reach到的距离并不远）。
//
//在遍历到每个元素时，先看看`curMax`有没有能够reach到当前元素的能力。
// 如果有，好，那这步我省下来了，你自己`nextMax`估量下你自己潜力就行，我不管我就是懒我就是不跳。
// 如果`curMax`终于跳不到当前元素了，那这时我就得把我宝贵的这一步让出来，`skip++; curMax = nextMax;`其实我能跳这么远哒。
//
//整体代码如下所示：


class Solution {
    public int jump(int[] nums) {
        int curMax = 0, nextMax = 0, n = nums.length, skip = 0;
        if(n == 0)  return 0;
        for(int i = 0; i < n; i++) {
            if(curMax < i) {
                skip++;
                curMax = nextMax;
            }
            nextMax = Math.max(nextMax, i + nums[i]);
        }
        return skip;
    }
}