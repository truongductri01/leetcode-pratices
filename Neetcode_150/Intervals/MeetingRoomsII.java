class Solution {
    public int minMeetingRooms(int[][] intervals) {
        /**
        We need to be able to determine when it is a good time to increase the counting of the conference rooms 

        [[0, 30], [0, 5], [10, 15], [10, 12], [13, 32], [31, 35]]

        room 1: [0, 30], 
        room 2: [0, 5], [10, 15], [31, 35]
        room 3: [10, 12], [13, 20], [21, 32],

        Can we use a heap to keep track of the room with the earliest available time?
        - if an interval cannot be fitted in the room at the top of the heap, it cannot be fitted in any other room, 
            => a new room needed
        - return the length of the queue at the end of the algorithm

        Test: 
        [[0, 30], [0, 5], [10, 15], [10, 12], [13, 32], [31, 35]]

        [0, 30]
        Heap: [room 1: 30],
        [0, 5]
        Heap: [room 2: 5, room 1: 30],
        [10, 15]
        Heap: [room 2: 15, room 1: 30],
        [10, 12]
        Heap: [room 3: 12, room 2: 15, room 1: 30],
        [13, 32]
        Heap: [room 2: 15, room 3: 32, room 1: 30],
        [31, 35]
        Heap: [room 2: 35, room 3: 32, room 1: 30]

        return 3

        // sort first to maintain the greediness

        Proof of correctness:
        1. Why using a heap works to maintain the minimum number of conference rooms?
        Since we want to have the number of conference roomes as small as possible, we want to add the meeting to the soonest room available instead of creating a new room.

        Only create a new room if the meeting cannot be fit into any previou rooms. 
        Also, note that since this is a min-heap, the ending time of the room at the peek of the heap is already the smallest one, therefore, if the current meeting cannot be fitted into the one at the peek, there is no room existing that could fit the meeting. So, creating a new room is neccessary. 
        
        2. Why is sorting neccessary?
        This is to make sure that the meeting comes in an ascending order of start and end time. Takes the second case as example, [[7,10],[2,4]], without sorting, 2 rooms will be created.
        We will sort the intervals in the ascending order of start time followed by the ascending order of end time. 
        */
        Arrays.sort(intervals, (a, b) -> a[0] == b[0]? a[1] - b[1]: a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            if (interval[0] >= pq.peek()) {
                // add to the current room
                pq.remove();
                pq.add(interval[1]);
            } else  {
                // create new room 
                pq.add(interval[1]);
            }
        }

        return pq.size();
    }
}
