Problems: https://leetcode.com/problems/3sum/

This is the follow-up questions (higher difficulty) of [Two Sum](https://leetcode.com/problems/two-sum/)

<hr>

## Requirements:

- All triplets with different index which sum is 0
- No duplicated triplet allowed

<hr>

## Idea:

- Keep an dictionary `myDict` which store the frequency of each number in the list
- Then following the order in the numbers list, for each number `n`, we take 2 actions:
  1. Reduce the frequency of `n` in `myDict`, if the frequency after deduction is 0, remove `n` from `myDict`
  2. Find all pairs which sum is `0 - n` - this is the **Two Sum** question
- After finding the triplets, we need a way to make sure any triplet return is unique

  With that, we use a string to represent each triplet.

  1. Sort the triplet array
  2. Join the sorted array into a string
  3. Store the string as the unique key for the unique triplet

  > `[0, 1, 1]` and `[1, 0, 1]` has the same key: `"011"`

<hr>

Solution: [here](./Three_Sums.py)
