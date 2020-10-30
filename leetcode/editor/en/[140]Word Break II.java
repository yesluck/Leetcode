//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, add spaces in s to construct a sentence where each word is a valid 
//dictionary word. Return all such possible sentences. 
//
// Note: 
//
// 
// The same word in the dictionary may be reused multiple times in the segmentat
//ion. 
// You may assume the dictionary does not contain duplicate words. 
// 
//
// Example 1: 
//
// 
//Input:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//Output:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// Example 2: 
//
// 
//Input:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//Output:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//Explanation: Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output:
//[] 
// Related Topics Dynamic Programming Backtracking

// Top-down DP
class Solution {
    Set<String> dict;
    Map<String, List<List<String>>> memo;

    public List<String> wordBreak(String s, List<String> wordDict) {
        dict = new HashSet(wordDict);
        memo = new HashMap<>();

        List<String> res = new ArrayList<>();

        helper(s);

        for (List<String> solution : memo.get(s)) {
            StringBuffer sentence = new StringBuffer();
            for (String word : solution) {
                sentence.insert(0, word);
                sentence.insert(0, " ");
            }
            res.add(sentence.toString().strip());
        }

        return res;
    }

    private List<List<String>> helper(String s) {
        if (s.equals("")) {
            List<List<String>> solutions = new ArrayList<>();
            solutions.add(new ArrayList<>());
            return solutions;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        } else {
            List<List<String>> solutions = new ArrayList<>();
            memo.put(s, solutions);
        }

        for (int i = 1; i <= s.length(); i++) {
            String word = s.substring(0, i);
            if (dict.contains(word)) {
                List<List<String>> subSolutions = helper(s.substring(i));
                for (List<String> subSolution : subSolutions) {
                    List<String> solution = new ArrayList(subSolution);
                    solution.add(word);
                    memo.get(s).add(solution);
                }
            }
        }

        return memo.get(s);
    }
}


// Bottom-up DP
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        // Set dict and charSets
        Set<String> dict = new HashSet(wordDict);
        Set<Character> stringCharSet = new HashSet<>();
        Set<Character> dictCharSet = new HashSet<>();
        updateCharSet(s, stringCharSet);
        for (String word : dict) {
            updateCharSet(word, dictCharSet);
        }

        // Fast fail
        if (!dictCharSet.containsAll(stringCharSet))
            return new ArrayList<>();

        // dp initialize
        List<List<String>> dp = new ArrayList(n + 1);
        for (int i = 0; i < n + 1; i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add("");
        /* Now:
            dp[0] = [""]
            dp = [[""], [], ..., []]
         */

        // dp
        for (int i = 1; i <= n; i++) {
            List<String> iSubSolutions = new ArrayList<>();   // all possible breaks for substring [0, i)
            for (int start = 0; start < i; start++) {
                String word = s.substring(start, i);
                if (dict.contains(word)){
                    for (String startSubSolution : dp.get(start)) {
                        iSubSolutions.add((startSubSolution + " " + word).strip());
                    }
                }
            }
            dp.set(i, iSubSolutions);
        }

        return dp.get(n);

    }

    private void updateCharSet(String s, Set<Character> charSet) {
        for (int i = 0; i < s.length(); i++) {
            charSet.add(s.charAt(i));
        }
    }
}

// Bottom-up DP (save break points)
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        // Set dict and charSets
        Set<String> dict = new HashSet(wordDict);
        Set<Character> stringCharSet = new HashSet<>();
        Set<Character> dictCharSet = new HashSet<>();
        updateCharSet(s, stringCharSet);
        for (String word : dict) {
            updateCharSet(word, dictCharSet);
        }

        // Fast fail
        if (!dictCharSet.containsAll(stringCharSet))
            return new ArrayList<>();

        // dp initialize
        List<List<List<Integer>>> dp = new ArrayList(n + 1);
        for (int i = 0; i < n + 1; i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add(new ArrayList(1));
        dp.get(0).get(0).add(0);
        /* Now:
            dp[0] = [[0]]
            dp = [[[0]], [], ..., []]
         */

        // dp
        for (int i = 1; i <= n; i++) {
            List<List<Integer>> iSubSolutions = new ArrayList<>();
            for (int start = 0; start < i; start++) {
                String word = s.substring(start, i);
                if (dict.contains(word)){
                    for (List<Integer> startSubSolution : dp.get(start)) {
                        List<Integer> iSubSolution = new ArrayList(startSubSolution);
                        iSubSolution.add(i);
                        iSubSolutions.add(iSubSolution);
                    }
                }
            }
            dp.set(i, iSubSolutions);
        }

        List<String> res = new ArrayList<>();
        for (List<Integer> solution : dp.get(n)) {
            StringBuffer sentence = new StringBuffer();
            for (int i = 0; i < solution.size() - 1; i++) {
                sentence.append(s.substring(solution.get(i), solution.get(i + 1)) + " ");
            }
            res.add(sentence.toString().strip());
        }

        return res;
    }

    private void updateCharSet(String s, Set<Character> charSet) {
        for (int i = 0; i < s.length(); i++) {
            charSet.add(s.charAt(i));
        }
    }
}
