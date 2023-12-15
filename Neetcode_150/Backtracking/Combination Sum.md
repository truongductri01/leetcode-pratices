<strong>Solution</strong>

```java
class Solution {
    class Sequence {
        int sum;
        int lastIdx;
        List<Integer> seq;
        public Sequence(int sum, int lastIdx, List<Integer> seq) {
            this.sum = sum;
            this.lastIdx = lastIdx;
            this.seq = seq;
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        // System.out.println(Arrays.toString(candidates));

        int length = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        Queue<Sequence> q = new LinkedList<>();

        Sequence initial = new Sequence(0, 0, new ArrayList<>());
        q.add(initial);

        while (!q.isEmpty()) {
            Sequence current = q.remove();
            // System.out.println(current.seq);
            
            for (int i = current.lastIdx; i < length; i++) {
                int value = candidates[i];
                if (current.sum + value < target) {
                    List<Integer> newList = new ArrayList<>(current.seq);
                    newList.add(value);
                    Sequence newSeq = new Sequence(current.sum + value, i, newList);
                    q.add(newSeq);
                } else if (current.sum + value == target) {
                    List<Integer> newList = new ArrayList<>(current.seq);
                    newList.add(value);
                    result.add(newList);
                }
            }
        }

        return result;
    }
}


/**
First sort the candidates array

We will create all possible combination and will stop when the total of the combination is over the target

create a class called Sequence
    int sum
    int last index; // the index of the last element in the sequence - help keeping track of where to navigate in the list
    ArrayList<Integer> seq;

Add this to a queue and process
*/
```
