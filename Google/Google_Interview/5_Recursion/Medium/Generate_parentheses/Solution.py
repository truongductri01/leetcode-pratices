class Solution:
    def generateParenthesis(self, n: int) -> list[str]:
        '''
        () -> 3 ways to add more parentheses
        - outside the current one
        - to left
        - to the right
        
        ()
        n = 2
        (())
        ()()

        n = 3
        ((()))
        (())()
        ()(())
        ()()()
        (()())

        how can we know whether left and right create same string? - can store in a set


        start with set("")
        n = 0 => set("")
        start with base case: n = 1 => set("()")
        then if n > 1 => 
            for each parenthesis style in the set, conduct 3 methods to generate new input
            then add to a new temporary set
        return

        ()() => (())(()) if n is 4 (())()

        n is:
        n - 1 with cover parantheses : (n-1_parant)
        n - 1 + 1
        n - 2 + 2
        n - 3 + 3 ...
        n // 2 + n // 2
        '''
        # use dp programming
        # key is n, value is set of parentheses
        dpMatrixDict = {
            1: set(["()"])
        }
        if n == 1:
            return dpMatrixDict[1]

        for i in range(2, n + 1):
            print("i >>>", i)
            dpMatrixDict[i] = set()
            for string in dpMatrixDict[i - 1]:
                dpMatrixDict[i].add("(" + string + ")")

            for idx in range(1, i // 2 + 1):
                for string1 in dpMatrixDict[idx]:
                    for string2 in dpMatrixDict[i - idx]:
                        dpMatrixDict[i].add(string1 + string2)
                        dpMatrixDict[i].add(string2 + string1)
        return dpMatrixDict[n]
        


s = Solution()
# 1 <= n <= 8
testCases = [
    [3, ["((()))","(()())","(())()","()(())","()()()"]],
    [1, ["()"]],
    [4, ["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]]
]
for test in testCases:
    n, expectedResult = test
    result = s.generateParenthesis(n)
    if (len(result) == len(expectedResult)):
        print("Passed >>>", n)
    else:
        print("Failed >>>", n)