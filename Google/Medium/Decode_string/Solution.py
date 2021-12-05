class Solution:
    def decodeString(self, s: str) -> str:
        numberStack = []

        def recursion(s: str, numberStack: list, start: int, end: int):
            i = start
            tempString = ""
            while i < end:
                char = s[i]
                if char.isnumeric():
                    charNumberVersion = char
                    i += 1
                    char = s[i]
                    while char.isnumeric():
                        charNumberVersion += char
                        i += 1
                        char = s[i]
                    numberStack.append(int(charNumberVersion))
                elif char == "[":
                    additionalString, newStart = recursion(s, numberStack, i + 1, end)
                    repeatTime = numberStack.pop()
                    tempString += additionalString * repeatTime
                    i = newStart
                elif char == "]":
                    return tempString, i + 1
                else:
                    tempString += char
                    i += 1
            return tempString
        return recursion(s, numberStack, 0, len(s))


# Test
test = {
    "10[leetcode]": "leetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcode",
    "3[a]2[bc]": "aaabcbc",
    "3[a2[c]]": "accaccacc",
    "2[abc]3[cd]ef": "abcabccdcdcdef",
    "abc3[cd]xyz": "abccdcdcdxyz"
}

s = Solution()
for input in test:
    result = s.decodeString(input)
    if result == test[input]:
        print("Passed >>>", input)
    else:
        print("Failed >>>", input)