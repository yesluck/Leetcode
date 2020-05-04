//Given an unsorted array of integers, find the length of the longest consecutiv
//e elements sequence. 
//
// Your algorithm should run in O(n) complexity. 
//
// Example: 
//
// 
//Input: [100, 4, 200, 1, 3, 2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Theref
//ore its length is 4.
// 
// Related Topics Array Union Find


// online HashMap
class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> hm = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            if (hm.containsKey(num))
                continue;

            int left = hm.getOrDefault(num - 1, 0), right = hm.getOrDefault(num + 1, 0);
            int len = left + right + 1;
            hm.put(num, len);
            hm.put(num - left, len);
            hm.put(num + right, len);

            res = Math.max(res, len);
        }

        return res;
    }
}


// offline HashMap
class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Set<Integer> hs = new HashSet<>();
        for (int num : nums)
            hs.add(num);

        for (int num : nums) {
            if (hs.contains(num - 1))
                continue;
            int temp = 1;
            while (hs.contains(num + 1)) {
                num += 1;
                temp += 1;
            }
            res = Math.max(res, temp);
        }

        return res;
    }
}