class Solution:
    def lengthLongestPath(self, input: str) -> int:
        currentFileName = ''
        currentFolder = ''
        currentTabCount = 0
        prevTabCount = 0
        dirStack = []
        longestPathCount = 0

        for char in list(input):
            if char != "\n" and char != "\t":
                currentFileName += char
            elif char == "\t":
                currentTabCount += 1
                pass
            else: # char == "\n"
                if currentTabCount > prevTabCount:
                    if "." not in currentFileName:
                        dirStack.append(currentFileName)
                elif currentTabCount == prevTabCount:
                    if "." not in currentFileName:
                        if len(dirStack) > 0:
                            dirStack.pop()
                        dirStack.append(currentFileName)
                else:
                    for _ in range(currentTabCount, len(dirStack)):
                        dirStack.pop()
                    if "." not in currentFileName:
                        dirStack.append(currentFileName)

                if "." in currentFileName:
                    tempLength = 0
                    for dir in dirStack:
                        tempLength += len(dir) + 1
                    tempLength += len(currentFileName)
                    longestPathCount = max(tempLength, longestPathCount)
                
                if "." not in currentFileName:
                    prevTabCount = currentTabCount
                    currentFolder = currentFileName
                currentTabCount = 0
                currentFileName = ''

        if len(currentFileName) > 0 and "." in currentFileName:
            if currentTabCount == prevTabCount:
                if "." not in currentFolder and len(dirStack) > 0:
                    dirStack.pop()
            elif currentTabCount < prevTabCount:
                for _ in range(currentTabCount, len(dirStack)):
                    dirStack.pop()

            tempLength = 0
            for dir in dirStack:
                tempLength += len(dir) + 1
            tempLength += len(currentFileName)
            longestPathCount = max(tempLength, longestPathCount)
        return longestPathCount

input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" # 32
# input = "dỉr\n\tsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
# input = "file1.txt\nfile2.txt\nlongfile.txt" # 12
# input = "dir\n\tsubdir1\n\t\tsubsubdir1\n\t\tfilename.ext\nnewRoot\n\treallyreallylongfilename.ext" # 36
# input = "dir\n        file.txt" # 16


s = Solution()
# print(input)
print(s.lengthLongestPath(input))