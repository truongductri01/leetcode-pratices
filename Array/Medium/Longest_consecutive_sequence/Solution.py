class Solution:
    def longestConsecutive(self, nums: list[int]) -> int:
        # Run time: O(n) => only linear algorithm works
        '''
        construct a dicitonary of all elements
        Start on the first element, if there is any element that is larger then the current one, increase the count


        [1, 3, 4, 2, 0]
        result = 1
        {
            1: 4,
            3: 1,
            4: 1,
            2: 1
        }
        1 -> 2 visited = {1, 2}
        -> 3 = visited = 3
        4 visted add 4
        When the len of visited == N => break the loop
        '''
        N = len(nums)
        if N <= 1:
            return N

        # establish a dictionary
        countDict = dict()
        longestCount = 1
        for el in nums:
            countDict[el] = 1

        for el in countDict:
            if el - 1 not in countDict:
                tempCount = 0
                tempIdx = el
                while tempIdx + 1 in countDict:
                    tempIdx += 1
                    tempCount += 1
                   
                countDict[el] += tempCount
                longestCount = max(longestCount, countDict[el])

        return longestCount



s = Solution()
testCases = [
    [[100,4,200,1,3,2], 4],
    [[0,3,7,2,5,8,4,6,0,1], 9]

]
for test in testCases:
    input = test[0]
    expect = test[1]
    if s.longestConsecutive(input) == expect:
        print("Passed >>>", input)
    else:
        print("Failed >>>", input)
