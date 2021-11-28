# BFS traversal solution

from collections import deque
class Solution:
    def allPathsSourceTarget(self, graph: list[list[int]]) -> list[list[int]]:
        queue = deque()
        queue.append([[0, []]])
        result = []
        while len(queue) > 0:
            currentLevel = queue.popleft()
            nextLevel = []
            for node in currentLevel:
                if node[0] == len(graph) - 1:
                    tempArr = node[1].copy()
                    tempArr.append(node[0])
                    result.append(tempArr)
                
                for nextNode in graph[node[0]]:
                    arr = node[1].copy()
                    arr.append(node[0])
                    nextLevel.append([nextNode, arr])
            if len(nextLevel) > 0:
                queue.append(nextLevel)
                
        return result
        
# DFS Solution
class Solution:
    def allPathsSourceTarget(self, graph: list[list[int]]) -> list[list[int]]:        
        N = len(graph)
        
        def dfs(current, path, output):
            if current == N - 1:
                output.append(path)
            for nx in graph[current]:
                dfs(nx, path+[nx], output)
        output = []
        dfs(0, [0], output)
        return output