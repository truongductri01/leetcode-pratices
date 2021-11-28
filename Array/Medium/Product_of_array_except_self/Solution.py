class Solution:
    def productExceptSelf(self, nums: list[int]) -> list[int]:
        # Linear space solution
        return self.linearSpace(nums)

        # constant space solution
        # return self.constantSpace(nums)

    def linearSpace(self, nums):
        prefixArr = [0 for _ in nums]
        postfixArr = [0 for _ in nums]
        prefix = postfix = 1
        N = len(nums)
        for i in range(N):
            prefixArr[i] = prefix
            prefix *= nums[i]

        for i in range(N):
            postfixArr[N - i - 1] = postfix
            postfix *= nums[N - i - 1]

        result = []
        for i in range(N):
            result.append(prefixArr[i] * postfixArr[i])

        return result

    def constantSpace(self, nums):
        N = len(nums)
        result = [0 for _ in range(N)]
        prefix = 1
        postfix = 1

        for i in range(N):
            result[i] = prefix
            prefix *= nums[i]

        for i in range(N):
            result[N - i - 1] *= postfix
            postfix *= nums[N - i - 1]
        return result

if __name__ == "__main__":
    input = [1, 2, 3, 4]
    s = Solution()
    print(s.productExceptSelf(input))