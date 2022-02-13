from collections import deque
class Solution:
    def anagramMappings(self, nums1: list[int], nums2: list[int]) -> list[int]:
        myMap = dict()
        for i in range(len(nums2)):
            if nums2[i] not in myMap:
                myMap[nums2[i]] = deque()
            myMap[nums2[i]].appendleft(i)
        
        result = [0] * len(nums1)
        for i in range(len(nums1)):
            index = myMap[nums1[i]].popleft()
            result[i] = index
        
        return result