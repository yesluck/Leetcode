//Given a string containing only digits, restore it by returning all possible valid IP address combinations. 
//
// Example: 
//
// 
//Input: "25525511135"
//Output: ["255.255.11.135", "255.255.111.35"]
// 
//


// my solution
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), s, 0);
        return res;
    }

    public void backtracking(List<String> res, List<String> temp, String s, int pos) {
        int n = s.length();
        if (temp.size() == 4) {
            if (pos == n) {
                String tempStr = "";
                for (int i = 0; i < 3; i++) {
                    tempStr += temp.get(i);
                    tempStr += ".";
                }
                tempStr += temp.get(3);
                res.add(tempStr);
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (pos + i > n)
                return;
            String t = s.substring(pos, pos + i);
            if ((Integer.parseInt(t) == 0 && t.length() == 1) || (t.charAt(0) != '0' && Integer.parseInt(t) < 256)) {
                temp.add(t);
                backtracking(res, temp, s, pos + i);
                temp.remove(temp.size() - 1);
            }
        }
    }
}