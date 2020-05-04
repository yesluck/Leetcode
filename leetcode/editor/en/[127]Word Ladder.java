//Given two words (beginWord and endWord), and a dictionary's word list, find th
//e length of shortest transformation sequence from beginWord to endWord, such tha
//t: 
//
// 
// Only one letter can be changed at a time. 
// Each transformed word must exist in the word list. 
// 
//
// Note: 
//
// 
// Return 0 if there is no such transformation sequence. 
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
//Output: 5
//
//Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog
//" -> "cog",
//return its length 5.
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
//Output: 0
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible trans
//formation.
// 
//
// 
// 
// Related Topics Breadth-first Search


// BFS
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return 0;

        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);

        int len = beginWord.length();   // length of the word
        int res = 0;
        while (!q.isEmpty()) {
            res += 1;
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String word = q.poll();
                for (int j = 0; j < len; j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newWord = word.substring(0, j) + c + word.substring(j + 1, len);
                        if (newWord.equals(endWord))
                            return res + 1;
                        if (!dict.contains(newWord))
                            continue;
                        dict.remove(newWord);
                        q.offer(newWord);
                    }
                }
            }
        }

        return 0;
    }
}


// Bidirectional BFS
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return 0;

        Set<String> q1 = new HashSet<>(), q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);

        int len = beginWord.length();   // length of the word
        int res = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            res += 1;

            // always expand the queue with smaller size first
            if (q1.size() > q2.size()) {
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }

            Set<String> temp = new HashSet<>();
            for (String word : q1) {
                for (int j = 0; j < len; j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newWord = word.substring(0, j) + c + word.substring(j + 1, len);
                        if (q2.contains(newWord))
                            return res + 1;
                        if (!dict.contains(newWord))
                            continue;
                        dict.remove(newWord);
                        temp.add(newWord);
                    }
                }
            }
            q1 = temp;
        }

        return 0;
    }
}