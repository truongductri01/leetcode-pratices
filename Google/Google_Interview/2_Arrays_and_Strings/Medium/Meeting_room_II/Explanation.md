Problem's Link: [here](https://leetcode.com/problems/meeting-rooms-ii/)

# Meeting Rooms II - Explanation

> So for each team having a meeting in such interval `[start, end]`. The team will go through all the available rooms and check if any room is free at the start of their meeting. Else, they will ask for a new room

The algorithm will be similar to the idea above

- Sort the intervals based on the starting time

  As we want to make sure the meeting starting sooner need to be scheduled first

- Then we can keep track of the room using a `MinHeap`, which is constructed using the **end time** of each room

  `[Room 1 ends at 4, Room 2 ends at 7]` is a literal representation of the heap

- As the heap is a `minHeap`, therefore, the root room will have the smallest (soonest) end time.

- So for each of the interval, we just need to check the root room to see if it is free

  - If it is free, update the end time of the room using the end time of the current interval
  - Else, create a new room and push to the heap. Push the root room back also

### Complexity Analysis:

- Run time:
  - For each interval, we need to compare to the root room.
  - Worst case is that we need to insert a new room to the heap, which cost `O(logN)`
  - Total run time: `O(NlogN)` with `N` is the number of intervals
- Space: O(N) - the maxium room we need to store in the heap is the numberz of intervals
