class Solution {
    public boolean isOverlap(int[] interval1, int[] interval2) {
        return interval2[0] < interval1[1];
    }

    public boolean isShorter(int[] interval1, int[] interval2) {
        return interval1[1] <= interval2[1];
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        /**
        greedy?

        can we immediately tell which intervals to be removed?
        can we re-arrange the intervals?

        [[1,2],[2,3],[3,4],[1,3]]

        sort based on primary the start and secondary the end date:
        [[1,2], [1,3], [2,3], [3,4]]

        check if the 2 intervals overlap, keep the one that is shorter, remove the one that is larger

        We can use the two pointers to keep track of which to be removed or which not.
        - first and second pointer
        - while second is withint the length of the intervals, continue
        - if the interval of the first and second pointer overlap:
            - increase the count
            - always remove the second since we sort based on both start and end date? No, we want the shorter intervals
            - check which one is shorter. If the first one is shorter, move second => second + 1
            - else, first = second, second = second + 1.
        - otherwise, first = second, second = second + 1. 

        - Run time: O(n log n) since we need to sort
        */

        Arrays.sort(intervals, (a, b) -> a[0] == b[0]? a[1] - b[1]: a[0] - b[0]);

        // print the result
        // System.out.println(Arrays.deepToString(intervals));

        if (intervals.length < 2) {
            return 0;
        }

        int count = 0;
        int first = 0;
        int second = 1;

        while (second < intervals.length) {
            int[] interval1 = intervals[first];
            int[] interval2 = intervals[second];

            if (isOverlap(interval1, interval2)) {
                count ++;
                // System.out.println(Arrays.toString(interval1) + " overlap " + Arrays.toString(interval2));

                if (isShorter(interval1, interval2)) {
                    // delete the second one 
                    second ++;
                } else {
                    first = second;
                    second ++;
                }
            } else {
                first = second;
                second ++;
            }
        }

        return count;
    }
}
