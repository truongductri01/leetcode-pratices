Problem: [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

<strong>Why move the pointer with lower line?</strong>

Assume that we have line A and B (with B > A) creating a volume V1 = A * d(A, B) - with d(A, B) is the distance between the two lines.

If we try to change B, what will happens? We will have a new line C, which will be closer to A - d(A, C) < d(A, B). Closer distance is already an advantage. If C <= B, super bad. 

How about C > B? Then thats mean C > A, and that will also means the new volume will be A * d(A, C) which is definitely smaller than A * d(A, B) which is V1.

Therefore, moving B will only make it worse.

So always moving the smaller line will be beneficial:
- if C > B, new volume V2 will be B * d(B, C) which might be larger than V1
- if C <= A, we can just keep V1 as result.


<strong>Solution:</strong>
```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        while (left < right) {
          int leftVal = height[left];
          int rightVal = height[right];
          int area = Math.min(leftVal, rightVal) * (right - left);
          result = Math.max(area, result);

          if (leftVal <= rightVal) {
            left ++;
          } else {
            right --;
          }
        }

        return result;
    }
}
```
