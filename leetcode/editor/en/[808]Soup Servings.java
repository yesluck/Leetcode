//There are two types of soup: type A and type B. Initially we have N ml of each
// type of soup. There are four kinds of operations: 
//
// 
// Serve 100 ml of soup A and 0 ml of soup B 
// Serve 75 ml of soup A and 25 ml of soup B 
// Serve 50 ml of soup A and 50 ml of soup B 
// Serve 25 ml of soup A and 75 ml of soup B 
// 
//
// When we serve some soup, we give it to someone and we no longer have it. Each
// turn, we will choose from the four operations with equal probability 0.25. If t
//he remaining volume of soup is not enough to complete the operation, we will ser
//ve as much as we can. We stop once we no longer have some quantity of both types
// of soup. 
//
// Note that we do not have the operation where all 100 ml's of soup B are used 
//first. 
//
// Return the probability that soup A will be empty first, plus half the probabi
//lity that A and B become empty at the same time. 
//
// 
//
// 
//Example:
//Input: N = 50
//Output: 0.625
//Explanation: 
//If we choose the first two operations, A will become empty first. For the thir
//d operation, A and B will become empty at the same time. For the fourth operatio
//n, B will become empty first. So the total probability of A becoming empty first
// plus half the probability that A and B become empty at the same time, is 0.25 *
// (1 + 1 + 0.5 + 0) = 0.625.
//
// 
//
// Notes: 
//
// 
// 0 <= N <= 10^9. 
// Answers within 10^-6 of the true value will be accepted as correct. 
// 
// Related Topics Dynamic Programming


class Solution {
    double[][] memo = new double[200][200];

    public double soupServings(int N) {
        if (N >= 4800)
            return 1.0;
        int numServing = (N + 24) / 25;
        return helper(numServing, numServing);
    }

    public double helper(int numServingA, int numServingB) {
        if (numServingA <= 0 && numServingB <= 0)
            return 0.5;
        if (numServingA <= 0)
            return 1.0;
        if (numServingB <= 0)
            return 0.0;

        if (memo[numServingA][numServingB] == 0)
            memo[numServingA][numServingB] = 0.25 * (helper(numServingA - 4, numServingB) + helper(numServingA - 3, numServingB - 1) + helper(numServingA - 2, numServingB - 2) + helper(numServingA - 1, numServingB - 3));

        return memo[numServingA][numServingB];
    }
}
