//There are two sorted arrays nums1 and nums2 of size m and n respectively. 
//
// Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)). 
//
// You may assume nums1 and nums2 cannot be both empty. 
//
// Example 1: 
//
// 
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0
// 
//
// Example 2: 
//
// 
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5
// 
//

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if(n1 > n2) return findMedianSortedArrays(nums2, nums1);
        int k = (n1 + n2 + 1) / 2;

        // start binary search
        // [x x m1-1] [m1 x x x]
        // [x x x x x m2-1] [m2 x x x x]
        int left = 0, right = n1;   // detail 1: right=n1
        while(left < right) {
            int m1 = left + (right - left) / 2;
            int m2 = k - m1;
            if(nums1[m1] < nums2[m2-1]) {   // detail 2: m1 and m2-1
                left = m1 + 1;
            }
            else {
                right = m1;
            }
        }

        int m1 = left, m2 = k - m1;

        // find max in two left parts (covers more when total is odd)
        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);   // detail 3: left part has more
        if((n1 + n2) % 2 == 1)  return c1;

        // find min in two right parts
        int c2 = Math.min(m1 >= n1 ? Integer.MAX_VALUE : nums1[m1],
                m2 >= n2 ? Integer.MAX_VALUE : nums2[m2]);
        return c1 / 2.0 + c2 / 2.0;
    }
}