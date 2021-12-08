# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def findLeaves(self, root: TreeNode or None) -> list[list[int]]:
        '''
        Keep track of a dictionary
        Keep track of the level of left and right branch using dfs
        '''
        if root is None:
            return []
        
        levelDict = dict()
        self.dfs(root, levelDict)
        currentLevel = 0
        result = []
        while currentLevel in levelDict:
            result.append(levelDict[currentLevel])
            currentLevel += 1
        print(levelDict)
        print(result)
        return result
    
    
    def dfs(self, root: TreeNode or None, levelDict):
        if root.left is None and root.right is None:
            if 0 not in levelDict:
                levelDict[0] = [root.val]
            else:
                levelDict[0].append(root.val)
            return 0
         
        else:
            # left
            if root.left:
                leftLevel = self.dfs(root.left, levelDict)
            else:
                leftLevel = 0
            # right
            if root.right:
                rightLevel = self.dfs(root.right, levelDict)
            else:
                rightLevel = 0
            rootLevel = max(leftLevel, rightLevel) + 1
            if rootLevel not in levelDict:
                levelDict[rootLevel] = [root.val]
            else:
                levelDict[rootLevel].append(root.val)
            return rootLevel
        
        