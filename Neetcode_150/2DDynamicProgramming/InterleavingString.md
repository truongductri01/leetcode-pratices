Problem: [Interleaving String](https://leetcode.com/problems/interleaving-string/)

<strong>Quick Explanation of the code:
1. Use Recursion to check all the possibilities of moving forward with the first pointer or the second pointer at each step.
2. Use DP map to avoid repeated recursion result => reduce runtime


Solution:
```java
class Solution {
    String s1, s2, s3;
    int len1, len2, len3;
    Map<Integer, Map<Integer, Map<Integer, Boolean>>> dpMap;

    public boolean isInDP(int p1, int p2, int p3) {
        if (!dpMap.containsKey(p1)) {
            return false;
        } else {
            if (!dpMap.get(p1).containsKey(p2)) {
                return false;
            } else {
                if (!dpMap.get(p1).get(p2).containsKey(p3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validate(int p1, int p2, int p3) {
        if (isInDP(p1, p2, p3)) {
            return dpMap.get(p1).get(p2).get(p3);
        }

        boolean newDp = false;

        if (p3 == len3) {
            newDp = true;
        } else if (p1 == len1) {
            boolean isBreak = false;
            while (p2 < len2) {
                if (s2.charAt(p2) != s3.charAt(p3)) {
                    newDp = false;
                    isBreak = true;
                    break;
                }
                p2 ++;
                p3 ++;
            }

            if (!isBreak) {
                newDp = true;
            }
        } else if (p2 == len2) {
            boolean isBreak = false;
            while (p1 < len1) {
                if (s1.charAt(p1) != s3.charAt(p3)) {
                    newDp = false;
                    isBreak = true;
                    break;
                }
                p1 ++;
                p3 ++;
            }
            if (!isBreak) {
                newDp = true;
            }
            // return true;
        } else {
            char c1, c2, c3;
            c1 = s1.charAt(p1);
            c2 = s2.charAt(p2);
            c3 = s3.charAt(p3);

            if (c1 == c2 && c1 == c3) {
                newDp = validate(p1 + 1, p2, p3 + 1) || validate(p1, p2 + 1, p3 + 1);
            } else if (c1 == c3) {
                newDp = validate(p1 + 1, p2, p3 + 1);
            } else if (c2 == c3) {
                newDp = validate(p1, p2 + 1, p3 + 1);
            } else {
                newDp = false;
            }
        }

        dpMap.putIfAbsent(p1, new HashMap<>());
        dpMap.get(p1).putIfAbsent(p2, new HashMap<>());
        dpMap.get(p1).get(p2).put(p3, newDp);

        return newDp;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.dpMap = new HashMap<>();

        len1 = s1.length();
        len2 = s2.length();
        len3 = s3.length();

        if (len3 != len1 + len2) {
            return false;
        }

        return validate(0, 0, 0);
    }
}
```
