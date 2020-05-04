//Given a list of lists of integers, nums, return all elements of nums in diagon
//al order as shown in the below images.
// 
// Example 1: 
//
// 
//
// 
//Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,4,2,7,5,3,8,6,9]
// 
//
// Example 2: 
//
// 
//
// 
//Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
//Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
// 
//
// Example 3: 
//
// 
//Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
//Output: [1,4,2,5,3,8,6,9,7,10,11]
// 
//
// Example 4: 
//
// 
//Input: nums = [[1,2,3,4,5,6]]
//Output: [1,2,3,4,5,6]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10^5 
// 1 <= nums[i].length <= 10^5 
// 1 <= nums[i][j] <= 10^9 
// There at most 10^5 elements in nums. 
// 
// Related Topics Array Sort


// answer during contest [TLE]
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int maxDimensionSum = 0, resSize = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            maxDimensionSum = Math.max(maxDimensionSum, nums.get(i).size() + i);
            resSize += nums.get(i).size();
        }

        int[] res = new int[resSize];
        int idx = 0;

        for (int ds = 0; ds <= maxDimensionSum; ds++) {
            for (int i = Math.min(ds, n - 1); i >= 0; i--) {
                int j = ds - i;
                if (j < 0 || j >= nums.get(i).size())
                    continue;
                res[idx++] = nums.get(i).get(j);
            }
        }
        return res;
    }
}


// use HashMap<i+j, List<>> as buckets
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int maxDimensionSum = 0, resSize = 0;
        int n = nums.size();
        Map<Integer, List<Integer>> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            maxDimensionSum = Math.max(maxDimensionSum, nums.get(i).size() + i);
            resSize += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (!hm.containsKey(i + j))
                    hm.put(i + j, new ArrayList<>());
                hm.get(i + j).add(nums.get(i).get(j));
            }
        }

        int[] res = new int[resSize];
        int idx = 0;
        for (int i = 0; i < maxDimensionSum; i++) {
            List<Integer> bucket = hm.get(i);
            for (int j = bucket.size() - 1; j >= 0; j--) {
                res[idx] = bucket.get(j);
                idx += 1;
            }
        }
        return res;
    }
}


// similarly, use List<List<>> as buckets
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int maxDimensionSum = 0, resSize = 0;
        int n = nums.size();
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maxDimensionSum = Math.max(maxDimensionSum, nums.get(i).size() + i);
            resSize += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (buckets.size() == i + j)
                    buckets.add(new ArrayList<>());
                buckets.get(i + j).add(nums.get(i).get(j));
            }
        }

        int[] res = new int[resSize];
        int idx = 0;
        for (int i = 0; i < maxDimensionSum; i++) {
            List<Integer> bucket = buckets.get(i);
            for (int j = bucket.size() - 1; j >= 0; j--) {
                res[idx] = bucket.get(j);
                idx += 1;
            }
        }
        return res;
    }
}