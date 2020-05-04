//Given an array of strings, group anagrams together. 
//
// Example: 
//
// 
//Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//Output:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// Note: 
//
// 
// All inputs will be in lowercase. 
// The order of your output does not matter. 
// 
//

// Use sorted string
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> hm = new HashMap<>();
        for(String str:strs) {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            List<String> temp = hm.getOrDefault(key, new ArrayList<>());
            temp.add(str);
            hm.put(key, temp);
        }
        for(String key:hm.keySet()) {
            res.add(hm.get(key));
        }
        return res;
    }
}

// Use string to represent buckets, very bad...
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hm = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(String str:strs) {
            int[] buckets = new int[26];
            String bucketStr = "";
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                buckets[c - 'a'] += 1;
            }
            for(int i = 0; i < 26; i++) {
                bucketStr += "#";
                bucketStr += String.valueOf(buckets[i]);
            }
            List<String> temp = hm.getOrDefault(bucketStr, new ArrayList<>());
            temp.add(str);
            hm.put(bucketStr, temp);
        }
        for(String key:hm.keySet()) {
            res.add(hm.get(key));
        }
        return res;
    }
}