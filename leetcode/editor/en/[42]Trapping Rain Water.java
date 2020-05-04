//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining. 
//
// 
//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image! 
//
// Example: 
//
// 
//Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6 
//

// dp
class Solution {
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        if(n == 0)  return res;
        int[] left = new int[n], right = new int[n];
        left[0] = height[0];
        for(int i = 1; i < n; i++)
            left[i] = Math.max(height[i], left[i-1]);
        right[n-1] = height[n-1];
        for(int i = n-2; i >= 0; i--)
            right[i] = Math.max(height[i], right[i+1]);
        for(int i = 1; i < n-1; i++)
            res += Math.min(left[i], right[i]) - height[i];
        return res;
    }
}

// two pointers
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, area = 0;
        while(left < right && height[left + 1] > height[left])  left++;
        while(left < right && height[right - 1] > height[right])    right--;
        while(left < right)
        {
            int Hleft = height[left], Hright = height[right];
            if(Hleft < Hright)
                while(left < right && height[++left] < Hleft)
                    area += Hleft - height[left];
            else
                while(left < right && height[--right] < Hright)
                    area += Hright - height[right];
        }
        return area;
    }
}