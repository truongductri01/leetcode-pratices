Problem: https://leetcode.com/problems/bulls-and-cows/

## Idea:

- First check all position that bulls happen
- Then go through the secret string one more time and store the frequency of each character, except character in the bulls position
- Then go through the guess string except the bulls position and if there exist the same char in the secret string (of course in a different position as we already check all bulls position), we update the cows count
