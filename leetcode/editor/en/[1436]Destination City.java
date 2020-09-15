//You are given the array paths, where paths[i] = [cityAi, cityBi] means there e
//xists a direct path going from cityAi to cityBi. Return the destination city, th
//at is, the city without any path outgoing to another city. 
//
// It is guaranteed that the graph of paths forms a line without any loop, there
//fore, there will be exactly one destination city. 
//
// 
// Example 1: 
//
// 
//Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]
//]
//Output: "Sao Paulo" 
//Explanation: Starting at "London" city you will reach "Sao Paulo" city which i
//s the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -
//> "Sao Paulo".
// 
//
// Example 2: 
//
// 
//Input: paths = [["B","C"],["D","B"],["C","A"]]
//Output: "A"
//Explanation: All possible trips are: 
//"D" -> "B" -> "C" -> "A". 
//"B" -> "C" -> "A". 
//"C" -> "A". 
//"A". 
//Clearly the destination city is "A".
// 
//
// Example 3: 
//
// 
//Input: paths = [["A","Z"]]
//Output: "Z"
// 
//
// 
// Constraints: 
//
// 
// 1 <= paths.length <= 100 
// paths[i].length == 2 
// 1 <= cityAi.length, cityBi.length <= 10 
// cityAi != cityBi 
// All strings consist of lowercase and uppercase English letters and the space 
//character. 
// 
// Related Topics String


// My answer using HashMap [0:00:00 - 0:05:12]
class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, Integer> hm = new HashMap<>();

        for (List<String> ls : paths) {
            String start = ls.get(0), end = ls.get(1);
            if (!hm.containsKey(start) || hm.get(start) == 0)
                hm.put(start, 1);
            if (!hm.containsKey(end))
                hm.put(end, 0);
        }

        for (String key : hm.keySet()) {
            if (hm.get(key) == 0)
                return key;
        }

        return "";
    }
}


// Better answer using HashSet and two passes
class Solution {
    public String destCity(List<List<String>> paths) {
        int n = paths.size();
        Set<String> hs = new HashSet<>();
        for (int i = 0; i < n; i++)
            hs.add(paths.get(i).get(1));
        for (int i = 0; i < n; i++)
            hs.remove(paths.get(i).get(0));
        return hs.iterator().next();
    }
}
