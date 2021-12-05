Problem: https://leetcode.com/problems/longest-consecutive-sequence/

## Idea:

- Keep track all unique number in `nums`
- Then we go through the list of unique elements in `nums` after putting them in either a dictionary or a set
  - Make sure we try to get the smallest element in the sequence by making sure if we are visiting `element`, `element - 1` is not in the dictionary or set
  - Then from that minimum `element` we have, we conduct a while loop and increase the count every time
  - And because we only use the smallest element in any possible chain, we no need to worry that we gonna visit an element we already visited
    > Example: [1, 2, 0]. Will we do nohing with 1 and 2 because 0 is the smallest one and is in the nums. So only when we visit 0, the real logic begins

Solution: [here](./Solution.py)
