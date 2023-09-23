Problem: [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/description/)

<strong>Observations</strong>
With `int n` is the cooldown time, and `char[] tasks` is the list of tasks:
1. We need to pick which task to be conducted during each cycle (within `n + 1` units of time)
2. Within a cycle, the task with the highest frequency should be prioritized to be conducted first.
   - > Proof: assume we have tasks = [A, A, B] and n = 1. If we order: B -> A -> idle -> A, the time cost will be 4. while if we order: A -> B -> A, the time cost is only 3.

<strong>Implementation:</strong>

So, in order to keep track of which task possesses the highest frequency, we will utilize a heap to avoid sorting constantly.

Here is the pseudo-code.
```java
maintain a max heap
while True:
    List<Node> poppedList: used for poping the heap
    Set<Char> visited: used for storing visited characters
    for i = 0 to i = n + 1:
        stop if (the heap is empty and poppedList is also empty)
        pop the root of the heap such that the values are not discovered yet,
        update the frequency and add it poppedList if the freq > 0
        add task to visited

    push the node back into the heap with the updated frequency from poppedList.
    continue until the heap is empty
```


<strong>Solution</strong>
```java
class Solution {
    class Node {
        char c;
        int freq;

        public Node(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        // count the frequencies of the tasks first
        Map<Character, Integer> taskFreqMap = new HashMap<>();
        for (char t: tasks) {
            taskFreqMap.putIfAbsent(t, 0);
            taskFreqMap.put(t, taskFreqMap.get(t) + 1);
        }

        // then create the heap
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq);

        for (char t: taskFreqMap.keySet()) {
            Node newNode = new Node(t, taskFreqMap.get(t));
            pq.add(newNode);
        }


        // start the while loop
        int result = 0;
        while (!pq.isEmpty()) {
            List<Node> popedNode = new ArrayList<>();
            Set<Character> visited = new HashSet<>();

            int idx = 0;
            while (idx < n + 1 && (!pq.isEmpty() || popedNode.size() > 0)) {
                if (!pq.isEmpty()) {
                    Node node = pq.remove();
                    if (!visited.contains(node.c)) {
                        // reduce the frequency 
                        node.freq --;
                        visited.add(node.c);
                    }

                    if (node.freq > 0) {
                        popedNode.add(node);
                    }
                }
            
                idx ++;
                result ++;
            }

            if (popedNode.size() > 0) {
                for (Node poped: popedNode) {
                    pq.add(poped);
                }
            }
        }

        return result;
    }
}
```
