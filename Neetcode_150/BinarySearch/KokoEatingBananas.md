Problem: [here](https://leetcode.com/problems/koko-eating-bananas/description/)


### Solution:

```java
class Solution {

    /**
    fastest: all of them are closed to the smallest element
        => fastest time 
        => if this does not satisfy

    slowest: all of them are closed to the largest element
    


    We need to increase the count if: we cannot finish eating all bananas within time
    Decrease if we can fininsh that on time => always decrease until reaching the final result?
     */
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int N = piles.length;
        int largest = piles[N - 1];
        int result = largest;

        int perHourL = 1;
        int perHourR = largest;
        while (perHourL <= perHourR) {
            int curCount = 0;
            boolean hasResult = true;
            int perHour = (perHourR + perHourL) / 2;
            // System.out.println("hour: " + hour + " with hourL: " + hourL + " and hourR: " + hourR);

            for (int i = 0; i < N; i++) {
                int value = piles[i];
                int tempCount;
                if (perHour >= value) {
                    tempCount = 1;
                } else if (value % perHour == 0) {
                    tempCount = value / perHour;
                } else {
                    tempCount = value / perHour + 1;
                }

                if (curCount + tempCount * (N - i) > h) { // cannot achieve
                    // System.out.println("Calling to increase the time");
                    hasResult = false;
                    perHourL = perHour + 1;
                    break;
                } else {
                    curCount += tempCount;
                }
            }

            if (hasResult) {
                perHourR = perHour - 1;
                // System.out.println("Calling to decrease the time with curCount: " + curCount);
                if (curCount > 0 && curCount <= h && perHour < result) {
                    result = perHour;
                }
            }
        }

        return result;
    }
}
```
