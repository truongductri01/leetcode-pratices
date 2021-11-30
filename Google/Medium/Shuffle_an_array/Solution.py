'''
Given an integer array nums, design an algorithm to randomly shuffle the array.
All permutations of the array should be equally likely as a result of the shuffling.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.
'''

import random
class MySolution:
    def __init__(self, nums: list[int]):
        self.orgArr = nums
        self.currentArr = nums.copy()

    def reset(self) -> list[int]:
        self.currentArr = self.orgArr.copy()
        return self.currentArr
        

    def shuffle(self) -> list[int]:
        matched = set()
        counter = 0
        while len(matched) != len(self.orgArr):
            idx = random.randint(0, len(self.orgArr) - 1)
            while idx in matched:
                idx = random.randint(0, len(self.orgArr) - 1)
            matched.add(idx)
            self.currentArr[counter] = self.orgArr[idx]
            counter += 1
        return self.currentArr

class OptimalSolution:
    def __init__(self, nums: list[int]):
        self.orgArr = nums
        self.currentArr = nums.copy()

    def reset(self) -> list[int]:
        self.currentArr = self.orgArr.copy()
        return self.currentArr
        

    def shuffle(self) -> list[int]:
        for i in range(len(self.orgArr)):
            # swap element at i with element of random position
            randomIdx = random.randint(i, len(self.orgArr) - 1)
            self.currentArr[i], self.currentArr[randomIdx] = self.currentArr[randomIdx], self.currentArr[i]

        return self.currentArr
                            

# Your Solution object will be instantiated and called as such:
nums = [1, 2, 3]
obj = OptimalSolution(nums)
param_1 = obj.reset()
param_2 = obj.shuffle()
print(param_2)