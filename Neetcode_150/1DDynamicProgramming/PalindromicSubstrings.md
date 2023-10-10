Problem: [647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/description/)

<strong>Approaches</strong>
1. Simplest approach will be checking all possible combinations of the substrings and check which one is palindromic

    O(n ^ 2) with each string to be around n / 2
    => O(n ^ 3) => more than 10 ^ 9
    and working with string is not so good


2. Can we apply DP here to keep track of the palindrom that is already checked
dp[][]

dp[i][j] will be either 0, 1, or 2
with 0 is unknown
with 1 is false
and 2 is true

dp[i][j] = 1 means that is not palindromic
dp[i][j] = 2 means palindromic checked 

if the string has length 1 => true for sure 

else: 
    check dp first
    check the first and last string then check recurse(left + 1, right - 1);

This will require to start with each substring starting with a specific index and check the length of it 

At most, a substring can have a length of n 
check from length 1 to length n 
with that, 

with length l, check from index 0 till index i as long as i + l < n; i++ 


<strong>Solution</strong>
```java
class Solution {
    int[][] dp;
    String string;
    
    public int recurse(int start, int end) {
        if (dp[start][end] != 0) {
            return dp[start][end];
        } else {
            int isPalindromic = 0;

            if (start == end) {
                isPalindromic = 2;
            } else if (string.charAt(start) != string.charAt(end)) {
                isPalindromic = 1;
            } else { // char at start and end are equal
                if (start + 1 == end) { 
                    isPalindromic = 2;
                } else {
                    isPalindromic = recurse(start + 1, end - 1);
                }
            } 

            dp[start][end] = isPalindromic;
            return dp[start][end];
        }
    }

    public int countSubstrings(String s) {
        string = s;
        dp = new int[string.length()][string.length()];

        for (int r = 0; r < dp.length; r ++) {
            for (int c = 0; c < dp.length; c++) {
                dp[r][c] = 0; // fill with unknown
            }
        }

        for (int length = 1; length <= s.length(); length ++) {
            for (int idx = 0; idx + length - 1 < s.length(); idx ++) {
                // StringBuilder sb = new StringBuilder();
                // for (int i = idx; i < idx + length; i++) {
                //     sb.append(s.charAt(i));
                // }
                // System.out.println(sb.toString());

                recurse(idx, idx + length - 1);
            }
        }    

        int result = 0;
        for (int r = 0; r < dp.length; r ++) {
            for (int c = 0; c < dp.length; c++) {
                if (dp[r][c] == 2) result ++;
            }
        }
        return result;
    }
}
```
