from heapq import heappush, heappop
class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        '''
        easiest: keep track of the sequence
        then we store it in an array and sort that, then return the k most frequent
        '''
        countDict = dict()
        for n in nums:
            if n in countDict:
                countDict[n] += 1
            else:
                countDict[n] = 1
                
        h = []
        for key in countDict:
            heappush(h, (-1 * countDict[key], key)) # multiply with -1 to use the heap as a max heap
            
        result = []
        for _ in range(k):
            result.append(heappop(h)[1])
            
        return result
        