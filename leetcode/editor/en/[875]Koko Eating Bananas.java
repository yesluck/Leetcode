//Koko loves to eat bananas. There are n piles of bananas, the ith pile has pile
//s[i] bananas. The guards have gone and will come back in h hours. 
//
// Koko can decide her bananas-per-hour eating speed of k. Each hour, she choose
//s some pile of bananas and eats k bananas from that pile. If the pile has less t
//han k bananas, she eats all of them instead and will not eat any more bananas du
//ring this hour. 
//
// Koko likes to eat slowly but still wants to finish eating all the bananas bef
//ore the guards return. 
//
// Return the minimum integer k such that she can eat all the bananas within h h
//ours. 
//
// 
// Example 1: 
//
// 
//Input: piles = [3,6,7,11], h = 8
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: piles = [30,11,23,4,20], h = 5
//Output: 30
// 
//
// Example 3: 
//
// 
//Input: piles = [30,11,23,4,20], h = 6
//Output: 23
// 
//
// 
// Constraints: 
//
// 
// 1 <= piles.length <= 104 
// piles.length <= h <= 109 
// 1 <= piles[i] <= 109 
// 
// Related Topics Binary Search


class Solution {
    private int getMax(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    private boolean canFinish(int[] piles, int H, int speed) {
        int time = 0;
        for (int pile : piles) {
            time += pile / speed + (pile % speed > 0 ? 1 : 0);
            if (time > H)
                return false;
        }
        return true;
    }

    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = getMax(piles) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean canFinishWithMid = canFinish(piles, H, mid);
            if (canFinishWithMid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
