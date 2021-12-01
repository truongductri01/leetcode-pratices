Problem: https://leetcode.com/problems/longest-absolute-file-path/

<hr>

## Requirements:

- Find the absolute path with the longest length
- Only count if the file name has the extension, aka. contains `.` in the name

<hr>

## Idea

- Keep track of the file name and the tab count of the current and previous file
- When ever we meet `\n`, we know that we gonna have a new file or folder name coming, therefore we can conduct some logic with the file (or folder) name we currently have
- When we meet `\t`, we increase the tab count
- There will be some special scenario which we will try to cover all in the solution explanation

<hr>

Solution: [here](./Solution.py)

Variables:

1. `currentFileName`: keep track of the file name right before we see a new `\n` character. Initial value: `""`
2. `currentFolder`: keep track of the current folder we are in. Initial value: `""`
3. `currentTabCount`: keep track of the current folder or file level, aka. the amount of tabs. Initial value: `0`
4. `prevtabCount`: keep track of the tab counts of our `currentFolder`. Initial value: `0`
5. `dirStack`: keep track of our path before hitting any specific file. The index of the folder in the stack represents the amount of tabs (aka. the higher the index, the lower the level). Initial value: `[]`
6. `longestPathCount`: keep track of the longest length as the final result to return. Initial value: `0`

Logic:

1. We know that `\n` and `\t` are special character, so when we see them, some logic needs to be applied
2. We gonna traverse through the input string, character by character
3. If the character we visit is neither `\n` or `\t`, concatenate the character to the `currentFileName`
4. If we see `\t`, we increase the `currentTabCount` by 1
5. If we see `\n`, here is where the main logic happens:

   - First compare the `currentTabCount` with `prevTabCount` in order to decide whether to stay in the current directory (folder) or move on to a higher level

   1. If `currentTabCount > prevTabCount`, this means the current file (or folder) is a child of our current folder
      > append the current file to `dirStack` if it is a folder (no `.` in its name)
   2. If `currentTabCount == prevTabCount`, this means the current file (or folder) is at the same level as our current folder
      > if the current file is a folder (no `.` in its name), replace the last element in `dirStack` with its name (`currentFileName`)
   3. Else, (`currentTabCount == prevTabCount`), the current file is at a higher level then our current working folder
      > pop out all folder in dirStack that is equal to or lower than our current level (`currentTabCount`)
      > Then if the current file is a folder, append it to the `dirStack`

   - If the `currentFileName` is in a file format (has `.` extension), calculate the length and update the `longestPathCount`
   - Else if the `currentFileName` is a folder, update the `prevTabCount` and `currentFolder`
   - Finally, reset `currentFileName` to `""` and `currentTabCount` to `0`
