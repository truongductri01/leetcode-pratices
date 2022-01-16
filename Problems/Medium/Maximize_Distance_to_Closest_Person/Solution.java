class Solution {
    public int maxDistToClosest(int[] seats) {
        /*
        Naive:
            - try to search for empty seats between two seats with person sitting or between a seat with person and the end of the array
                - if in the middle of two seats => return the division
                - if in the middle of a seat and the end of array => return the difference
                - if in the middle of the begin of array and a seat => return the difference
            - runtime: O(n)
        */
        int result = 0;
        int prevSeatIdx = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (prevSeatIdx == -1) {
                    result = Math.max(result, i);
                } else {
                    int dist = (i - prevSeatIdx) / 2;
                    result = Math.max(result, dist);
                }
                prevSeatIdx = i;
            } else if (i == seats.length - 1) {
                result = Math.max(result, i - prevSeatIdx);
            }
        }
       
        return result;
    }
}