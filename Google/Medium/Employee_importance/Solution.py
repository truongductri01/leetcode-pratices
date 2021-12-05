"""
# Definition for Employee.
"""
class Employee:
    def __init__(self, id: int, importance: int, subordinates: list[int]):
        self.id = id
        self.importance = importance
        self.subordinates = subordinates

from collections import deque
class Solution:
    def getImportance(self, employees: list, id: int) -> int:
        # BFS problem
        # turn the list into a dictionary for searching purposes
        employeeDict = dict()
        for employee in employees:
            employee: Employee
            employeeDict[employee.id] = employee

        # the for each subordinates append all new subordinates and continues until no one is left
        currentArr = [employeeDict[id]]
        result = 0
        while len(currentArr) > 0:
            tempArr = []
            for employee in currentArr:
                employee: Employee
                result += employee.importance
                if len(employee.subordinates) > 0:
                    for id in employee.subordinates:
                        tempArr.append(employeeDict[id])
            currentArr = tempArr
        return result


if __name__ == "__main__":
    s = Solution()
    user3 = Employee(3, 3, [])
    user2 = Employee(2, 3, [])
    user1 = Employee(1, 5, [2, 3])
    inputArr = [user1, user2, user3]
    inputId = 1
    print(s.getImportance(inputArr, inputId))