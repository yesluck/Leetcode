//The set [1,2,3,...,n] contains a total of n! unique permutations. 
//
// By listing and labeling all of the permutations in order, we get the following sequence for n = 3: 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// Given n and k, return the kth permutation sequence. 
//
// Note: 
//
// 
// Given n will be between 1 and 9 inclusive. 
// Given k will be between 1 and n! inclusive. 
// 
//
// Example 1: 
//
// 
//Input: n = 3, k = 3
//Output: "213"
// 
//
// Example 2: 
//
// 
//Input: n = 4, k = 9
//Output: "2314"
// 
//

class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n + 1];

        factorial[0] = 1;
        for(int i = 1; i <= n; i++) {
            nums.add(i);
            factorial[i] = i * factorial[i-1];
        }

        k--;
        for(int i = n - 1; i >= 0; i--) {
            int idx = k / factorial[i];
            res.append(nums.get(idx));
            nums.remove(idx);
            k -= idx * factorial[i];
        }

        return res.toString();
    }
}