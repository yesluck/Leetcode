//Given n points on a 2D plane, find the maximum number of points that lie on th
//e same straight line. 
//
// Example 1: 
//
// 
//Input: [[1,1],[2,2],[3,3]]
//Output: 3
//Explanation:
//^
//|
//|        o
//|     o
//|  o  
//+------------->
//0  1  2  3  4
// 
//
// Example 2: 
//
// 
//Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//Output: 4
//Explanation:
//^
//|
//|  o
//|     o        o
//|        o
//|  o        o
//+------------------->
//0  1  2  3  4  5  6
// 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Hash Table Math


class Solution {
    public int maxPoints(int[][] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;
        int n = points.length;

        int res = 0;

        for (int i = 0; i < n; i++) {
            // Compound slope key to a String
            Map<String, Integer> hm = new HashMap<>();
            int dup = 0;
            int max = 0;

            for (int j = i + 1; j < n; j++) {
                int deltaX = points[j][0] - points[i][0];
                int deltaY = points[j][1] - points[i][1];

                if (deltaX == 0 && deltaY == 0) {
                    dup += 1;
                    continue;
                }

                int gcd = gcd(deltaX, deltaY);
                int dx = deltaX / gcd;
                int dy = deltaY / gcd;
                String slope = dx + "," + dy;

                hm.put(slope, hm.getOrDefault(slope, 0) + 1);
                max = Math.max(max, hm.get(slope));
            }

            res = Math.max(res, max + dup + 1);
        }

        return res;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
