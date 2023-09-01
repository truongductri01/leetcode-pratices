Problem: [here](https://leetcode.com/problems/palindrome-partitioning/)

<strong> Quick explanation</strong>

First though: 
- start with position 0, for each sub string starting with that character, create a partition
    - repeat for the remaining 

- create a queue, storing the list of sub string in the partition, with the starting index to start adding new element in 


"aaabaaa"

queue: []

["aaa", 3] -> [["aaabaaa"], 7] -> [["a", "a"], 2] -> [["a", "aa"], 3] -> [["a", "aabaa"], 6] -> [["aa", "a"], 3] -> [["aa", "aba"], 6] -> [["aaa", "b"], 4]

take:
[["a"], 1], remianing string: "aabaaa"


create: [["a", "a"], 2] -> [["a", "aa"], 3] -> [["a", "aabaa"], 6]

take [["aa"], 2], remaining: "abaaa"

create: [["aa", "a"], 3] -> [["aa", "aba"], 6]

take: [["aaa"], 3], remaining: "baaa"

create: [["aaa", "b"], 4]

stop when the index to start >= leng of the string itself

Solution:
```java
class Solution {
    Set<String> palinSet;
    Set<String> notPalinSet;


    public boolean isPalindrome(String string) {
        if (palinSet.contains(string)) return true;
        else if (notPalinSet.contains(string)) return false;

        int left = 0;
        int right = string.length() - 1;

        while (left <= right) {
            if (string.charAt(left) != string.charAt(right)) {
                notPalinSet.add(string);
                return false;
            }
            left ++;
            right --;
        }

        palinSet.add(string);
        return true;
    }

    class Node {
        List<String> elements;
        int indexToStart;

        public Node(List<String> elements, int indexToStart) {
            this.elements = elements;
            this.indexToStart = indexToStart;
        }
    }
    public List<List<String>> partition(String s) {
        Queue<Node> q = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();
        palinSet = new HashSet<>();
        notPalinSet = new HashSet<>();

        List<String> startList = new ArrayList<>();
        q.add(new Node(startList, 0));

        while (!q.isEmpty()) {
            Node curNode = q.remove();
            if (curNode.indexToStart >= s.length()) {
                result.add(curNode.elements);
                continue;
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = curNode.indexToStart; i <= s.length() - 1; i++) {
                    sb.append(String.valueOf(s.charAt(i)));
                    String subString = sb.toString();

                    if (isPalindrome(subString)) {
                        List<String> newList = new ArrayList<>(curNode.elements);
                        newList.add(subString);
                        q.add(new Node(newList, i + 1));
                    }
                }
            }
        }

        return result;
    }
}
```
