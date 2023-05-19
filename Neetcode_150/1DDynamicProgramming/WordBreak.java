class Solution {
    public boolean isMatch(String s, String word, int startIdx) {
        if (startIdx + word.length() > s.length()) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (s.charAt(startIdx + i) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        /**
        Can we brute force by calculating all the combination of the words in the dictionary?
        Concatenate them to get the string, if equals => true


        This is DP?
        Choosing which word in the dictionary to place into the string could affect whether next word could be chosen

        in DP, we need a state that is constant and does not change
        
        First thought:
        - mark the position that could be constructed from the word in wordDict
        - put that index into a queue
        - when an index in the queue is the same as the length of string s, we find a way to break the string s


        Try this:
        - Queue to store the index that could be constructed
        - A visited list that list out the index that could be constructed already
        - Loop through the words in wordDict, if the word match with the word from index 0 to index i, put i into the queue and into visited
        - while the queue is not empty:
            + pop the index: i,
            + if i is s.length - 1, return true
            + for word (length w) in the wordDict, check if the word matches with that from i to i + w
                - put i + w to queue and to visited if i + w is not in visited
        - return false
        
        Note: index I in the queue means that, we can construct anything in the string from 0 to I - 1 already, need to check what starts with I only. And with that, when I == s.length(), we have been able to match s already
        */
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for (String word: wordDict) {
            if (isMatch(s, word, 0)) {
                int index = word.length();
                queue.add(index);
                visited.add(index);
            }
        }

        while (!queue.isEmpty()) {
            int index = queue.remove();
            // System.out.println(index);
            if (index == s.length()) {
                return true;
            }
            for (String word: wordDict) {
                if (isMatch(s, word, index)) {
                    int newIndex = index + word.length();
                    if (!visited.contains(newIndex)) {
                        queue.add(newIndex);
                        visited.add(newIndex);
                    }
                }
            }
        }

        return false;
    }
}
