//Count the number of prime numbers less than a non-negative number, n. 
//
// 
// Example 1: 
//
// 
//Input: n = 10
//Output: 4
//Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
// 
//
// Example 2: 
//
// 
//Input: n = 0
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: n = 1
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 0 <= n <= 5 * 106 
// 
// Related Topics Hash Table Math


class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        // 优化1：i * i < n
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                // 优化2：int j = i * i
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            count += isPrime[i] ? 1 : 0;
        }

        return count;
    }
}
