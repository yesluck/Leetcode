//In the "100 game," two players take turns adding, to a running total, any inte
//ger from 1..10. The player who first causes the running total to reach or exceed
// 100 wins. 
//
// What if we change the game so that players cannot re-use integers? 
//
// For example, two players might take turns drawing from a common pool of numbe
//rs of 1..15 without replacement until they reach a total >= 100. 
//
// Given an integer maxChoosableInteger and another integer desiredTotal, determ
//ine if the first player to move can force a win, assuming both players play opti
//mally. 
//
// You can always assume that maxChoosableInteger will not be larger than 20 and
// desiredTotal will not be larger than 300.
// 
//
// Example
// 
//Input:
//maxChoosableInteger = 10
//desiredTotal = 11
//
//Output:
//false
//
//Explanation:
//No matter which integer the first player choose, the first player will lose.
//The first player can choose an integer from 1 up to 10.
//If the first player choose 1, the second player can only choose integers from 
//2 up to 10.
//The second player will win by choosing 10 and get a total = 11, which is >= de
//siredTotal.
//Same with other integers chosen by the first player, the second player will al
//ways win.
// 
// Related Topics Dynamic Programming Minimax


class Solution {
    Map<Integer, Boolean> hm;
    boolean[] used;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal)
            return false;
        if (desiredTotal <= 0)
            return true;

        hm = new HashMap<>();
        used = new boolean[maxChoosableInteger + 1];

        return helper(desiredTotal);
    }

    public boolean helper(int desired) {
        if (desired <= 0)
            return false;
        int n = used.length;
        int key = format(used);
        if (hm.containsKey(key))
            return hm.get(key);

        for (int i = 1; i < n; i++) {
            if (used[i])
                continue;
            used[i] = true;
            // !helper()代表如果我取i，对方无论如何肯定输，代表在此时我的取法还有胜利可能
            if (!helper(desired - i)) {
                hm.put(key, true);
                used[i] = false;
                return true;
            }
            used[i] = false;
        }
        hm.put(key, false);
        return false;
    }

    public int format(boolean[] used){
        int num = 0;
        for (boolean b: used){
            num = num << 1;
            if (b)
                num |= 1;
        }
        return num;
    }
}
