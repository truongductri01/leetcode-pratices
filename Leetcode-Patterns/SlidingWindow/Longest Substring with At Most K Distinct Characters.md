```java
/**
First thought: find all substring and count the distinct word using a hash set
- O(n ^ 2 * times for string manipulation) 

Second: We gonna keep expanding or shrinking the substring while keeping track of the elements within it and their frequency
- By using a map to keep track of this, we will be able to adjust the frequency when either expanding or shrinking

Need:
- 2 pointers: start and end
- map: storing the frequency of character within the range of start and end pointer with their frequency
    Char to Int
- a mechanism to keep the map updated

to know how many distinct char are there, we use the map size
=> when shrinking, if the frequency goes to 0, remove the key from the map

Note: k can be 0 => if it is 0, return 0

Psuedo code:
- length = s.length()
- start and end pointer set at 0
- result set to 1

while end < length:
    expand by adding the character at the current end pointer to the map
        - update the frequency

    if (size of map <= k) {
        - update result = Math.max(result, end - start + 1);
    } else {
        while (size of map > k && start <= end) {
            shrink by removing the char at the start pointer
            char startChar = s.charAt(start); // avoid error here
            reduce freq by 1
            if freq after reduced is 0, remove the key from map
            start ++;
        }  
    }

    end ++;
return result;
 */
```

```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int result = 0;
        int length = s.length();
        int start = 0;
        int end = 0;

        Map<Character, Integer> map = new HashMap<>();
        while (end < length) {
            char addedChar = s.charAt(end);
            map.putIfAbsent(addedChar, 0);
            int charFreq = map.get(addedChar);
            map.put(addedChar, charFreq + 1);

            if (map.size() <= k) {
                result = Math.max(result, end - start + 1);
            } else {
                while (map.size() > k && start <= end) {
                    char startChar = s.charAt(start);
                    int startCharFreq = map.get(startChar);
                    if (startCharFreq == 1) {
                        map.remove(startChar);
                    } else {
                        map.put(startChar, startCharFreq - 1);
                    }
                    start ++;
                }
            }

            end ++;
        }


        return result;
    }
}
```
