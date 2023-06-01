class Solution {
    public boolean canJump(int[] nums) {
        /**
        The steps count from that index to index + step. 
        We can maintain a queue to keep track of which index can be visited, also keep track of a set of index already visit to avoid repetition. 

        Runtime: O(n ^ 2) with n can be 10^5

        Need to improve this:
        - do not care about the value at the last index
        - care whether the other cell could reach the last one. 
        - update the value of the elements to the farthest cell they can reach


        If we can visit cell x, can we claim that we can always visit cell y < x?
        */
        int max = 0;
        int idx = 0;
        while (idx < nums.length && idx <= max) {
            max = Math.max(max, idx + nums[idx]);

            if (max >= nums.length - 1) {
                return true;
            }
            idx ++;
        }

        return false;
    }
}
