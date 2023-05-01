class Solution {
    public int maxProfit(int[] prices) {
        /**
        Naive: find all possible pairs and check the sum
        O(n^5) over the allowed limit

        Thought:
            keep track of the global minima. 
            When a stock value is larger then that, its mean we could sell, just compare that with the result
                else, replace the minima with that value
         */

        int globalMinima = prices[0];
        int result = 0;
        for (int n: prices) {
            if (n > globalMinima) {
                result = Math.max(result, n - globalMinima);
            } else {
                globalMinima = n;
            }
        }
        return result;
    }
}
