class Solution:
    def maxDistToClosest(self, seats: List[int]) -> int:
        result = 0
        prevSeatIdx = -1
        for i in range(len(seats)):
            if seats[i] == 1:
                if prevSeatIdx == -1:
                    result = max(result, i)
                else:
                    dist = (i - prevSeatIdx) // 2
                    result = max(result, dist)
                prevSeatIdx = i
            elif i == len(seats) - 1:
                result = max(result, i - prevSeatIdx)
        return result
        