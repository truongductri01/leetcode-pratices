class Solution {
    public int[] productExceptSelf(int[] nums) {
        /**
        Write something that could run in O(n) times

        First thought:
        - store the multiply of all elements into an array
        - divide the multiplication by the element itself
        caveat? what if the element is 0?
        => how about counting 0, if there is more than 1 0, all values will be 0
            otherwise, just calculate the sum without multiplying with 0?

        
        Cannot use division also!!!

        Runtime: O(n)


        Can we utilize prefix and suffix hint in the constraints?
        Start from the left to set up the prefix
        Start from the right to set up the suffix
        Then multiply prefix and suffix for each position
        Runtim: O(n)
         */
        
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        prefix[0] = 1;
        suffix[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            // for the prefix
            prefix[i] = prefix[i - 1] * nums[i - 1];
            // for the suffix
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        return result;
    }
}
