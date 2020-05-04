//Given a non-negative integer numRows, generate the first numRows of Pascal's triangle. 
//
// 
//In Pascal's triangle, each number is the sum of the two numbers directly above it. 
//
// Example: 
//
// 
//Input: 5
//Output:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//]
// 
//

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0)
            return res;

        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        res.add(new ArrayList<>(temp));
        if (numRows == 1)
            return res;

        temp.add(1);
        res.add(new ArrayList<>(temp));
        if (numRows == 2)
            return res;

        for (int i = 2; i < numRows; i++) {
            temp = new ArrayList<>();
            temp.add(1);
            for (int j = 1; j < i; j++)
                temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            temp.add(1);
            res.add(new ArrayList<>(temp));
        }

        return res;
    }
}