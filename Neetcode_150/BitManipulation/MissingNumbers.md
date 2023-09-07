Problem: [here](https://leetcode.com/problems/missing-number/description/)

This is a fascinating problem, which has multiple ways to solve

<strong>Several approaches</strong>
1. Sort and find the missing numbers. Simple and easy to implement. O(1) in space and O(n log n) in runtime.
2. Using HashSet. Put all the numbers in the array into a set. Go through the numbers from 0 to n, if it is not in the set, return that number. Space and time complexity will both be O(n).
3. Using math. We can easily calculate the sum from 0 to n. Then subtract that with the sum of the elements in the array to get the missing number.
4. Using some trick of swapping the positions of the elements in the array. (This will be discussed later in this post)
5. Bit Manipulation (I am not sure about this yet so I will save that for later)

<strong>Swapping elements approach to solve this problem </strong>

Some observations that we have are:
1. We have a range from 0 to n, which is n + 1 elements. The array only has n positions (indices). So, in order to conduct the in place element swap, we somehow need to reduce the needed space to only support n elements.
2. Since the numbers are in range, they will be consecutive, if we sort them, an element with a value i will be in the positions of i - 1 (0-based index).

With these observations, we can remove element 0 from considering for place elements swaps:
- We just need to make sure there is already a 0 in the arrays
- since if swap 0, we will need index -1, which is not supported (or not good to be used)
- Here is a simple explanation of the process:
```java
 /** 
        - swap the positions of the element in place? => will be missing 1 spot
        -> How about we ignore the number 0?
            for each element in the array:
                - if element == index + 1: Continue
                - if element == 0: ignore 
                - else, conduct swap 
                    at index i, we have value val. 
                    swap numbers[i] with numbers[val - 1].
                    Then continue to swap numbers[i] until it matches the value 
        - in the end, go through and check if there is any missing element in the array
        */
```


```java
class Solution {
    int[] nums;

    public void swap(int idx) {
        int val = nums[idx];

        if (val == idx + 1) return;
        else if (val == 0) return;
        else {
            int temp = nums[val - 1];
            nums[val - 1] = val;
            nums[idx] = temp;
            swap(idx);
        }
    }

    public int missingNumber(int[] nums) {
        this.nums = nums;
        boolean hasZero = false;
        for (int n: nums) {
            if (n == 0) {
                hasZero = true;
                break;
            }
        }

        if (!hasZero) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i + 1) {
                continue;
            } else {
                swap(i);
            }
        }

        // System.out.println(Arrays.toString(this.nums));

        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result = i + 1;
                break;
            }
        }
        return result;
    }
}
```
