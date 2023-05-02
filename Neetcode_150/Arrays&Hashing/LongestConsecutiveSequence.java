class Solution {
    public int dfs(Map<Integer, Integer> map, Set<Integer> visited, int value) {
        if (visited.contains(value)) {
            return map.get(value);
        } else if (!map.containsKey(value)) {
            return 0;
        } else {
            visited.add(value);
            int result = 1 + dfs(map, visited, value + 1);
            map.put(value, result);
            return result;
        }
    }
    public int longestConsecutive(int[] nums) {
        /**
        We can sort and find the longest consecutive in it 
        => O(n log n) times

        Using map or set to do this:
        Or DP

        We will count how consecutive are there if start counting from a value
        count(n) = 1 + count (n + 1) if exists
         */
        
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int n: nums) {
            map.put(n, 1);
        }

        int result = 0;
        // conduct the counting
        for (int n: map.keySet()) {
            result = Math.max(result, dfs(map, visited, n));
        }
        return result;
    }
}
