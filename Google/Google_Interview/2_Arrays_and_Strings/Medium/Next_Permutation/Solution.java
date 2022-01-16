class Solution {
    public void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i ++;
            j --;
        }
    }
    public void nextPermutation(int[] nums) {
        /*
        596 - 659
        1. Determine if the next permutation is available
            - available if there is a contiguous subarray which is sorted
            - if the sorted position happends at index i => after swapping the element with the correct element, reverse the subarray starting at i+1 to the end
                - because the part after the sorted position is still sorted but in descending order
            - when the sorted part appears, we need to find the smallest element which is still larger then the element at the position we are working with
                - find that element by traversing the array backwards until we find the element which is larger than the current element
                - because the sorted situation happens at index i, the subarray from i+1 to the end of the array is sorted by in descending order => traversing backward makes sure we find the smallest solution
        2. Else return a sorted array
        
        Time Complexity: O(nlog(n))
        */
        int N = nums.length;
        if (N >= 2) {
            boolean hasSwapped = false;
            for (int i = N-2; i>=0; i--) {
                if (nums[i] < nums[i + 1]) {
                    int smallestElementLargerThanTheCurrentOneIdx = N - 1;
                    while (nums[smallestElementLargerThanTheCurrentOneIdx] <= nums[i]) {
                        smallestElementLargerThanTheCurrentOneIdx --;
                    }
                    int temp = nums[i];
                    nums[i] = nums[smallestElementLargerThanTheCurrentOneIdx];
                    nums[smallestElementLargerThanTheCurrentOneIdx] = temp;
                    reverse(nums, i+1);
                    hasSwapped = true;
                    break;
                }
            }
            if (!hasSwapped) {
                reverse(nums, 0);
            }
        }
    }
}