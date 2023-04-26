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

        Runtime: O(n)
         */

        boolean hasZero = false;
        boolean hasMultipleZero = false;

        for (int n: nums) {
            if (n == 0) {
                if (hasZero) {
                    hasMultipleZero = true;
                }
                hasZero = true;
            }
        }

        if (hasMultipleZero) {
            return new int[nums.length];
        } else {
            int mult = 1;
            for (int n: nums) {
                if (n != 0) {
                    mult *= n;
                }
            }

            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                if (n != 0) {
                    int value = 0;
                    if (!hasZero) value = mult / n;
                    result[i] = value;
                } else {
                    result[i] = mult;
                }
            }
            return result;
        }
    }
}
