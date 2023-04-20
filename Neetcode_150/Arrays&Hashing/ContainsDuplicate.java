class Solution {
    public boolean containsDuplicate(int[] nums) {
        /**
        There are 3 possible ways to solve this problem

        1. Using for loop: O(n)
        Take an element at index i.
        Loop through the rest of the element in the list to see if there is any variable of the same value

        2. Sort the array O(n log(n))
        Elements of the same value will appear next to each other
        Sort. Then loop through all elements in the list and compare i with i + 1

        3. Use a HashSet: O(1)

         */
        Set<Integer> set = new HashSet<>();
        for (int n: nums) {
            if (set.contains(n)) {
                return true;
            } else {
                set.add(n);
            }
        }
        return false;
    }
}
