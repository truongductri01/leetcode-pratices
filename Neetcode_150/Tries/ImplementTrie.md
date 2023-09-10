Problem: [here](https://leetcode.com/problems/implement-trie-prefix-tree/description/)

<strong>Solution</strong>

```java
class Trie {
    class TrieNode {
        public Map<Character, TrieNode> children;
        public int wordCount;
        public boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            wordCount = 0;
            isEndOfWord = false;
        }
    }

    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = this.root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // check if the node exist for the current index
            // if not, create a new reference there
            current.children.putIfAbsent(c, new TrieNode());

            current = current.children.get(c);
        }

        current.wordCount ++;
        current.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        TrieNode current = this.root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!current.children.containsKey(c)) {
                return false;
            }

            current = current.children.get(c);
        }

        return current.isEndOfWord;
        
    }
    
    public boolean startsWith(String prefix) {
        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!current.children.containsKey(c)) {
                return false;
            }

            current = current.children.get(c);
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```
