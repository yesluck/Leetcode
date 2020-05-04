//Given two words (beginWord and endWord), and a dictionary's word list, find al
//l shortest transformation sequence(s) from beginWord to endWord, such that: 
//
// 
// Only one letter can be changed at a time 
// Each transformed word must exist in the word list. Note that beginWord is not
// a transformed word. 
// 
//
// Note: 
//
// 
// Return an empty list if there is no such transformation sequence. 
// All words have the same length. 
// All words contain only lowercase alphabetic characters. 
// You may assume no duplicates in the word list. 
// You may assume beginWord and endWord are non-empty and are not the same. 
// 
//
// Example 1: 
//
// 
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// Example 2: 
//
// 
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: []
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible trans
//formation.
// 
//
// 
// 
// Related Topics Array String Backtracking Breadth-first Search


// BFS + DFS
class Solution {
    // Collections that necessary
    List<List<String>> res;
    Map<String, Integer> steps;
    Map<String, List<String>> parents;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // initialize necessary collections
        res = new ArrayList<>();
        steps = new HashMap<>();
        parents = new HashMap<>();

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return res;

        // operations to beginWord and endWord
        dict.remove(beginWord);
        dict.remove(endWord);
        steps.put(beginWord, 1);

        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);

        int len = beginWord.length();
        int numStep = 0;
        boolean found = false;          // found the endWord??
        while (!q.isEmpty() && !found) {
            numStep += 1;
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String word = q.poll();
                for (int j = 0; j < len; j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newWord = word.substring(0, j) + c + word.substring(j + 1, len);
                        if (newWord.equals(endWord)) {
                            // if found: mark found and update parent
                            found = true;
                            if (!parents.containsKey(newWord))
                                parents.put(newWord, new ArrayList<>());
                            parents.get(newWord).add(word);
                        } else {
                            // else not found:
                            // if not a new word, but another transform with the same number of steps, add word as newWord's another parent
                            if (steps.containsKey(newWord) && steps.get(newWord) == numStep + 1)
                                parents.get(newWord).add(word);
                        }
                        if (!dict.contains(newWord))
                            continue;
                        dict.remove(newWord);
                        q.offer(newWord);
                        steps.put(newWord, steps.get(word) + 1);
                        // update parent for every newWord
                        if (!parents.containsKey(newWord))
                            parents.put(newWord, new ArrayList<>());
                        parents.get(newWord).add(word);
                    }
                }
            }
        }

        // go to DFS function
        if (found) {
            List<String> curr = new ArrayList<>();
            curr.add(endWord);
            getPaths(endWord, beginWord, curr);
        }

        return res;
    }

    public void getPaths(String word, String beginWord, List<String> curr) {
        // if found a complete path: add this path into result
        if (word.equals(beginWord)) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // traverse back to the word's parents and backtracking
        for (String parent : parents.get(word)) {
            curr.add(0, parent);
            getPaths(parent, beginWord, curr);
            curr.remove(0);
        }
    }
}


// Bidirectional BFS + DFS [very difficult]
class Solution {
    List<List<String>> res;
    Map<String, List<String>> children;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        res = new ArrayList<>();
        children = new HashMap<>();

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return res;

        dict.remove(beginWord);
        dict.remove(endWord);

        Set<String> q1 = new HashSet<>(), q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);

        int len = beginWord.length();
        boolean found = false;
        boolean backward = false;
        while (!q1.isEmpty() && !q2.isEmpty() && !found) {
            if (q1.size() > q2.size()) {
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
                backward = !backward;
            }

            for (String word : q1)
                dict.remove(word);
            for (String word : q2)
                dict.remove(word);

            Set<String> temp = new HashSet<>();

            for (String word : q1) {
                for (int j = 0; j < len; j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newWord = word.substring(0, j) + c + word.substring(j + 1, len);
                        String parent = word, child = newWord;
                        if (backward) {
                            String tmp = parent;
                            parent = child;
                            child = tmp;
                        }

                        if (q2.contains(newWord)) {
                            found = true;
                            if (!children.containsKey(parent))
                                children.put(parent, new ArrayList<>());
                            children.get(parent).add(child);
                        } else {
                            // not a new word (so still remains in dict), but another transform with the same number of steps, add word as newWord's another parent
                            if (dict.contains(newWord) && !found) {
                                temp.add(newWord);
                                if (!children.containsKey(parent))
                                    children.put(parent, new ArrayList<>());
                                children.get(parent).add(child);
                            }
                        }
                    }
                }
            }
            q1 = temp;
        }

        if (found) {
            List<String> curr = new ArrayList<>();
            curr.add(beginWord);
            getPaths(beginWord, endWord, curr);
        }

        return res;
    }

    public void getPaths(String word, String endWord, List<String> curr) {
        if (word.equals(endWord)) {
            res.add(new ArrayList<>(curr));
            return;
        }

        if (!children.containsKey(word))
            return;

        for (String child : children.get(word)) {
            curr.add(child);
            getPaths(child, endWord, curr);
            curr.remove(curr.size() - 1);
        }
    }
}