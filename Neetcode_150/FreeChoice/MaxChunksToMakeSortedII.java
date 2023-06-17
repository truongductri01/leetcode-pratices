class Solution {
    public int maxChunksToSorted(int[] arr) {
        /**
        
        1. The chunks that is splited should be consecutive
        2. Two chunks must be consecutive with each other
            => maximum of one chunk will be smaller then the minimum of the next one. 
        3. Keep track of the maximum value of a chunk, if the next element has a larger value, split it. 

        [5, 4, 3, 6, 3, 7, 7]

        min array:
        [3, 3, 3, 3, 3, 7, 7]

        => we can just count the difference?
        

        What if we know the min - max value to the left and right of an element? Will it be easier to determine?

        If there is an element with smaller value to the right of an element, they must be in the same chunk 



        Keep track of the current number, 
        - if at some point, no more element to the right of its is strictly smaller than it, count ++ and update the element to the current one. 
        - else, increase the index but not update anything
        */

        // DP
        int[] min = new int[arr.length];
        min[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            min[i] = Math.min(arr[i], min[i + 1]);
        }

        System.out.println(Arrays.toString(min));

        int count = 1;
        int index = 1;
        int current = arr[0];

        while (index < arr.length) {
            // explain this part, why update the max value of the chunk?
            if (current <= min[index]) {
                count ++;
                current = arr[index];
            } else {
                // The step to extend the chunk. 
                current = Math.max(current, arr[index]);
            }

            index ++;
        }

        return count;
    }
}
