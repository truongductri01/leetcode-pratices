Problem: https://leetcode.com/problems/shuffle-an-array/

<hr>

## Requirements

- Implement 3 methods: constructor, reset, and shuffle

<hr>

## Idea:

- store `self.orgArr` and `self.currentArr`
- reset turns `self.currentArr` back to `self.orgArr`
- shuffle create new array using a simple function, randomly choose an index within [0, len(nums) - 1] repeatedly, make sure no index is selected twice

<hr>

## Improvement

- Note that on the first thought, we kinda have to choose a random index which could have been selected before, which create many overlap loop
- There is an alternative way to do so, called Fisher-Yates algorithm
  - Loop through the list
  - Choose a random index (`randomIdx`) between the current index (`i`) and the last index
  - Then swap the element at `randomIdx` with element at `i`
- This approach will save us some additional space
