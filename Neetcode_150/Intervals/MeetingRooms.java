class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        /**
        If the meetings time overlapped, return no.
        */
        if (intervals.length <= 1) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> a[0] == b[0]? a[1] - b[1]: a[0] - b[0]);

        for (int i = 0; i < intervals.length - 1; i++) {
            int[] interval1 = intervals[i];
            int[] interval2 = intervals[i + 1];
            if (interval2[0] < interval1[1]) {
                return false;
            }
           
        }

        return true;
    }
}
