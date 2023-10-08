Problem: [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/description/)

<strong>Approach</strong>
```java
/**
Definitely binary search

2 steps:
- search for which row the target should fall into 
- then search within that row for the target 


How to search for which row 
hasRow = false;
int rowIdx = -1;
leftRow and rightRow

while (leftRow <= rightRow) {
    int midRow;

    if (target within midRow) {
        hasRow = true;
        rowIdx = midRow;
    }

    if (target < midRow[0]) {
        right = midRow - 1;
    } else if (target > midRow[size - 1]) {
        left = midRow + 1;
    }
}

if not found row: return false;

else, find within the row

*/
```


```java

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean hasRow = false;
        int rowIdx = -1;

        int leftRow = 0;
        int rightRow = matrix.length - 1;
        int columns = matrix[0].length;

        while (leftRow <= rightRow) {
            int midRow = (leftRow + rightRow) / 2;

            if (target >= matrix[midRow][0] && target <= matrix[midRow][columns - 1]) {
                hasRow = true;
                rowIdx = midRow;
                break;
            } else if (target < matrix[midRow][0]) {
                rightRow = midRow - 1;
            } else if (target > matrix[midRow][columns - 1]) {
                leftRow = midRow + 1;
            }
        }

        if (!hasRow) {
            return false;
        }

        int left = 0;
        int right = columns - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (target == matrix[rowIdx][mid]) {
                return true;
            } else if (target < matrix[rowIdx][mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }
}
```
