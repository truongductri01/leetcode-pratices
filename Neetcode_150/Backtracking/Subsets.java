class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        /**
            We just need to keep track of the index of the current element
            We can use a map to store the index of the value
            Use that to conduct the search
        */

        Map<Integer, Integer> indexMap = new HashMap<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList();

        queue.add(new ArrayList());

        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], i);
        }

        while (!queue.isEmpty()) {
            List<Integer> current = queue.remove();
            result.add(current);
            int idx = -1;
            if (current.size() > 0) {
                int lastelement = current.get(current.size() - 1);
                idx = indexMap.get(lastelement);
            }

            for (int i = idx + 1; i < nums.length; i++) {
                List<Integer> newList = new ArrayList(current);
                newList.add(nums[i]);
                queue.add(newList);
            }
        }

        return result;
    }
}
