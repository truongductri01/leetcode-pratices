# Problem: https://leetcode.com/problems/3sum/

class Solution:
    def threeSum(self, nums: list[int]) -> list[list[int]]:
        if len(nums) < 3:
            return []
        result = []
        myDict = dict()
        tripletKeys = set()
        # set up the dictionary
        for n in nums:
            if n not in myDict:
                myDict[n] = 0
            myDict[n] += 1
        
        # for each element, try to find the triplet
        for n in nums:
            if myDict[n] == 1:
                myDict.pop(n)
            else:
                myDict[n] -= 1
                    
            twoSumArr = self.twoSum(n, myDict)
            
            for arr in twoSumArr:
                arr.sort()
                key = "".join(list(map(str, arr)))
                if key not in tripletKeys:
                    tripletKeys.add(key)
                    result.append(arr)
            # check the triplet with the key set
        return result
    
    def twoSum(self, currentVal, myDict):
        target = 0 - currentVal
        temp = []
        keySet = set()
        for key in myDict:
            if target - key in myDict and key not in keySet:
                if target - key != key or myDict[key] >= 2:
                    keySet.add(key)
                    keySet.add(target - key)
                    temp.append([currentVal, key, target - key])
        return temp