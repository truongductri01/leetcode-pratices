class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        '''
        longest substring without repetition
        substring: a consecutive sequence of characters within a string

        I am thinking about window sliding technique
           v
        abcbacde
          ^
        runtime: O(2N) - O(N)
        Space: O(N)

        
        left and right pointer = 0
        traverse through the string using an index
        keep a dictionary to store item:
            key: character
            value: index of that char in the string but within the two pointers
        keep traversing, at any point, if the char we currently visit is already exist in our diction
            - update the result, see if the current string we have between the pointers is longer
            - remove all char as key in dictionary from left pointer to the index of the element with repitition
        return the result
        '''
        if len(s) <= 1:
            return len(s)

        left = 0
        charDict = dict()
        result = 0

        for i in range(len(s)):
            if s[i] not in charDict:
                charDict[s[i]] = i
            else:
                # calculate the current length, update result
                result = max(result, i - left)

                # remove all keys from left pointer to the desire index
                newLeft = charDict[s[i]] + 1
                for j in range(left, newLeft):
                    charDict.pop(s[j])

                # update left pointer
                left = newLeft

                # update the count for the char
                charDict[s[i]] = i
        result = max(result, len(charDict))
        del charDict
        return result

s = Solution()
print(s.lengthOfLongestSubstring("au"))

        