Problem: [here](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/)

<strong>First approach</strong>

Utilize Binary search to conduct search for value needed to make the target sum.

```java
class Solution {
    public int find(int startIdx, int endIdx, int toFind,  int[] numbers) {
        int left = startIdx;
        int right = endIdx;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = numbers[mid];

            if (midValue == toFind) {
                return mid;
            } else if (midValue > toFind) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int[] twoSum(int[] numbers, int target) {
        /**
        this can be solve with a normal twoSum approach using Map and for loop for a fast O(n) solution.

        Howerver, since this is a sorted array, can we try binary search?


        For i = 0; i < numbers.lenght; i++
            value = numbers[i];
            needToFind = target - value;
            Start finding from (i + 1, numbers.length - 1);
                return > 0 index if found
            simply return that as the result

        Can utilize another map to stored which value has been visited / explored

        => may cost O(n log n) to find the result. 
         */


        int[] result = new int[2];

        for (int i = 0; i < numbers.length - 1; i++) {
            int value = numbers[i];
            int toFind = target - value;

            int idx = find(i + 1, numbers.length - 1, toFind, numbers);

            if (idx > 0) {
                result[0] = i + 1;
                result[1] = idx + 1;
                break;
            }
        }

        return result;
    }
}
```

> This comes with an issue is that we might go through the same value several times using the binary search


<strong>Second approach</strong>

We can use two pointers to find the wanted target sum

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        /**
        This can be solved with two pointers
        

        start from left and right
        - if sum > target: move right down
        - if sum < target: move left up
        - else: return the result;

        left and right should never met in the middle.
        */

        int left = 0;
        int right = numbers.length - 1;
        int[] results = new int[2];

        while (left < right) {
            int leftVal = numbers[left];
            int rightVal = numbers[right];
            int sum = leftVal + rightVal;

            if (sum == target) {
                results[0] = left + 1;
                results[1] = right + 1;
                break;
            } else if (sum < target) {
                left ++;
            } else {
                right  --;
            }
        }

        return results;
        



    }
}
```

