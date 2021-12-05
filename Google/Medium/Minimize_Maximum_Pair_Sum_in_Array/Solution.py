# Problems: https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/

class Solution:
    def minPairSum(self, nums: list[int]) -> int:
        # inorder to minimize, try to add the largest with the smallest
        maximumSum = float("-inf")

        # sort the array
        nums.sort()

        # go outside in and find the largest sum
        # if len is 4 [1, 2, 3, 4] => just stop at index 1
        start = 0
        end = len(nums) - 1
        while start < end:
            maximumSum = max(maximumSum, nums[start] + nums[end])
            start += 1
            end -= 1
            
        return maximumSum


s = Solution()
inputDict = [
    [[3,5,4,2,4,6], 8],
    [[3,5,2,3], 7]
]
for testCase in inputDict:
    input = testCase[0]
    expect = testCase[1]
    if (s.minPairSum(input) == expect):
        print("Passed >>>", input)
    else:
        print("Failed >>>", input)
