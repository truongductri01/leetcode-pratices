class Solution:
    def canAttendMeetings(self, intervals: list[list[int]]) -> bool:
        '''
        [[0,30],[5,10],[15,20]]
        - sort based on the start of each interval
        - then make sure the end of the first one is the start of next one

        Optimize:
        - expansion of intervals
        - keep track the curStart, curEnd
        - for each subArr, if subArr[1] > curStart but < curEnd return False as overlapping
            if curEnd > subArr[0] < curStart => return False 

        [[9,10],[4,9],[4,17]]
        [9, 10]
        [4, 9]
        4 - 10
        '''
        if len(intervals) <= 1:
            return True
        
        intervals.sort(key=lambda subArr: subArr[0])
        curEnd = None
        for subArr in intervals:
            if curEnd is None:
                curEnd = subArr[1]
            else:
                if curEnd > subArr[0]:
                    return False
                else:
                    curEnd = subArr[1]

        return True



s = Solution()
testCases = [
    [
        [[0,30],[5,10],[15,20]],
        False
    ],
    [
        [[7,10],[2,4]],
        True
    ],
    [
        [[9,10],[4,9],[4,17]],
        False
    ],
    [
        [[13,15],[1,13]],
        True
    ]
]

for test in testCases:
    inputArr, expected = test
    result = s.canAttendMeetings(inputArr)

    if result == expected:
        print("Passed >>>", inputArr)
    else:
        print("Failed >>>", inputArr)