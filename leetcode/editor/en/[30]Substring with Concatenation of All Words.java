//You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters. 
//
// Example 1: 
//
// 
//Input:
//  s = "barfoothefoobarman",
//  words = ["foo","bar"]
//Output: [0,9]
//Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
//The output order does not matter, returning [9,0] is fine too.
// 
//
// Example 2: 
//
// 
//Input:
//  s = "wordgoodgoodgoodbestword",
//  words = ["word","good","best","word"]
//Output: []
// 
//

// O(n*num)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if(s.length() == 0 || words.length == 0)    return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int len = words[0].length();
        Map<String, Integer> counts = new HashMap<>();
        for(String word:words)  counts.put(word, counts.getOrDefault(word, 0) + 1);
        for(int i = 0; i < s.length() - words.length * len + 1; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while(j < words.length) {
                String word = s.substring(i + j*len, i + (j+1)*len);
                if(counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if(seen.get(word) > counts.getOrDefault(word, 0))   break;  // fail because we have more words (or more times of a word) than needed
                }
                else break; // fail because we don't see the needed words
                j++;
            }
            if(j == words.length)   res.add(i);
        }
        return res;
    }
}


// O(n)
// case 1: all right and move to next substring -> just delete the firstWord in this current window from "seen".
// case 2: wrong word appeared, destroy current window and have a brand new start after this window.
// case 3: right word but seen > counts, move current window to get rid of one occurance.
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if(s.length() == 0 || words.length == 0)    return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int len = words[0].length();
        Map<String, Integer> counts = new HashMap<>();
        for(String word:words)  counts.put(word, counts.getOrDefault(word, 0) + 1);
        // start looping, len conditions:
        for(int c = 0; c < len; c++) {
            // only keep len seen's, so we can save some unnecessary checkings
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            for(int i = c; i < s.length() - words.length * len + 1; i += len) {
                boolean hasRemoved = false;     // TODO: has removed the word in case 3
                while(j < words.length) {
                    String word = s.substring(i + j*len, i + (j+1)*len);
                    if(counts.containsKey(word)) {
                        seen.put(word, seen.getOrDefault(word, 0) + 1);
                        j++;
                        // case 3
                        if(seen.get(word) > counts.getOrDefault(word, 0)) {
                            hasRemoved = true;  // TODO: removed word in case 3
                            int removeNum = 0;
                            while(seen.get(word) > counts.getOrDefault(word, 0)) {
                                String firstWord = s.substring(i + removeNum * len, i + (removeNum+1) * len);
                                seen.put(firstWord, seen.get(firstWord) - 1);
                                removeNum ++;
                            }
                            j -= removeNum;
                            i = i + (removeNum - 1) * len;  // -1 because the for(i) can provide one more i+=len
                            break;
                        }
                    }
                    // case 2
                    else {
                        seen.clear();
                        i = i + j * len;    // j*len because the for(i) can provide one more i+=len
                        j = 0;
                        break;
                    }
                }
                if(j == words.length)   res.add(i);
                // case 1
                if(j > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i+len);
                    seen.put(firstWord, seen.get(firstWord) - 1);
                    j--;
                }
            }
        }
        return res;
    }
}