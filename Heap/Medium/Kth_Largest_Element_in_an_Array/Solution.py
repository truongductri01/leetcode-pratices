from heapq import heappush, heappop, heappushpop
class Solution:
    def findKthLargest(self, nums: list[int], k: int) -> int:
        heap = []
        for n in nums:
            # keep the length of the heap at k elements => the root will be the kth largest element we need to find
            if (len(heap) < k):  
                heappush(heap, n)
            else:
                heappushpop(heap, n)
                
        return heappop(heap)
        