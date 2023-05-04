// Solution 1
class Solution {
    class Pair{
        int value;
        int frequency;
        public Pair(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        /**
        
        First thought:
        We will count the frequency of the nums and sort them, then output
        We can count, group then sort the arrays


        Second thought:
        - Count the frequency: O(n)
        - Use min heap to keep track of most k frequent elements: O(n log n)
        - Return the elements in a result arrays
        Run time:
        Space: O(n)
        
        Is there a better way to do this?
        Better than O(n log n)?
        - O(n) or O(log n)

        */
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.frequency - b.frequency); 
        // with a is the value and b is frequency
        Map<Integer, Integer> map = new HashMap<>();

        for (int n: nums) {
            map.putIfAbsent(n, 0);
            map.put(n, map.get(n) + 1);
        }

        // min heap for most frequency
        for (int n: map.keySet()) {
            Pair currentPair = new Pair(n, map.get(n));
            // System.out.printf("curValue: %d, curFreq: %d\n", currentPair.value, currentPair.frequency);
            if (pq.size() < k) {
                pq.add(currentPair);
            }
            else {
                Pair topPair = pq.peek();
                if (currentPair.frequency > topPair.frequency) {
                    // System.out.printf("curValue: %d, curFreq: %d, topValue: %d, topFreq: %d\n", currentPair.value, currentPair.frequency, topPair.value, topPair.frequency);
                    // ignore
                    pq.poll();
                    pq.add(currentPair);
                }
            }
        }

        // return as result
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            Pair top = pq.poll();
            // System.out.println(top.value + " " + top.frequency);
            result[i] = top.value;
        }
        return result;
    }
}

// Solution 2
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int maxFreq = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> freqList = new ArrayList<>();

        for (int n: nums) {
            map.putIfAbsent(n, 0);
            map.put(n, map.get(n) + 1);
        }

        for (int n: map.keySet()) {
            maxFreq = Math.max(maxFreq, map.get(n));
        }

        for (int i = 0; i <= maxFreq ; i++) {
            freqList.add(new ArrayList<>());
        }
        for (int n: map.keySet()) {
            freqList.get(map.get(n)).add(n);
        }

        // get the result
        int[] result = new int[k];
        int idx = 0;
        for (int i = maxFreq; i >= 1 && idx < k; i--) {
            for (int n: freqList.get(i)) {
                result[idx] = n;
                idx ++;
            }
        }

        return result;
    }
}
