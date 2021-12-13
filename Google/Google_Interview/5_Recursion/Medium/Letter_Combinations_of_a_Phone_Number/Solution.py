# Problem: https://leetcode.com/problems/letter-combinations-of-a-phone-number/

class Solution:
    def letterCombinations(self, digits: str) -> list[str]:
        '''
        a letters dictionary to quickly retrieve letters
        recursively go through each digit and append in the letter
        '''
        letterDict = {
            "2": ["a", "b", "c"],
            "3": ["d", "e", "f"],
            "4": ["g", "h", "i"],
            "5": ['j', 'k', 'l'],
            "6": ['m', 'n', 'o'],
            "7": ["p", "q", "r", "s"],
            "8": ["t", "u", "v"],
            "9": ["w", "x", "y", "z"]
        }
        result = []
        self.recursion(0, digits, "", letterDict, result)
        return result

    def recursion(self, start: int, digits: str, currentString: str, letterDict: dict, resultArr: list):
        if start >= len(digits):
            if len(currentString) > 0:
                resultArr.append(currentString)
            return
        else:
            for char in letterDict[digits[start]]:
                self.recursion(start + 1, digits, currentString + char, letterDict, resultArr)

s = Solution()
# 2 <= digits number <= 9
# 0 <= digits length <= 4
testCases = [
    ["23", ["ad","ae","af","bd","be","bf","cd","ce","cf"]],
    ["", []],
    ["2", ["a","b","c"]]
]
for test in testCases:
    digits, expectedResult = test
    result = s.letterCombinations(digits)
    if result == expectedResult:
        print("Passed >>>", digits)
    else:
        print("Failed >>>", digits)