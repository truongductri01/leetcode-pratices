from heapq import heappush, heappop
class Solution:
    def minMeetingRooms(self, intervals: list[list[int]]) -> int:
        intervals.sort(key=lambda subArr: subArr[0])
        heap = []
        for i in intervals:
            temp = []
            hasRoom = False
            while len(heap) > 0:
                currentEnd = heappop(heap)
                if i[0] >= currentEnd:
                    temp.append(i[1])
                    hasRoom = True
                    break
                else:
                    temp.append(currentEnd)
            if not hasRoom:
                heappush(heap, i[1])
            for endTime in temp:
                heappush(heap, endTime)

        return len(heap)


s = Solution()
testCases = [
    [
        [[0,30],[5,10],[15,20]],
        2
    ],
    [
        [[7,10],[2,4]],
        1
    ],
    [
        [[9,10],[4,9],[4,17]],
        2
    ]
]

for test in testCases:
    inputArr, expected = test
    result = s.minMeetingRooms(inputArr)

    if result == expected:
        print("Passed >>>", inputArr)
    else:
        print("Failed >>>", inputArr)