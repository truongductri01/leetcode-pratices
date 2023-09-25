Problem: [207. Course Schedule](https://leetcode.com/problems/course-schedule/description/)

<strong>Implementation:</strong>

```java
/**
What makes the code return false:
- cannot take the class. why?
- there is a cycle. need a for b but then need b for a. like a deadlock => false. 

This looks like a graph. => just need to determine if there is a cycle. 
- we can use BFS for this. Keep traversing until reaching the end. While at the end, make sure to also utilize DP to keep track whether all courses could be taken succesfully

Steps:
1. Establish the mapping representing the courses relationship. If a needs b, c, d then:
{
    a: list(b, c, d, ...)
}

2. Start BFS: on all key:
    DP map: Map<String, Boolean> map 

    for all keys in map: call could_take(key, new HashSet<>())

    could take a?, visited: Set<String>:
        if key in dpMap: return dpMap.get(key)
        else if a not in the map:
            dpMap.put(key, true)
        else if key in visited:
            return false;
        else: 
            can = true?
            visited.add(can)
            for prere in map.get(a):
                can_prere = could take prereq(prere, visited)
                if !can_prere:
                    can = false
                    break
            dpMap.put(key, can);
        return dpMap.get(key);

3. At the end:
    loop through all keys in DP, if there is a course that could not be taken, return false.
*/
```

```java
class Solution {
    Map<Integer, Boolean> dpMap;
    Map<Integer, Set<Integer>> map;

    public boolean couldTake(int course, Set<Integer> visited) {
        if (dpMap.containsKey(course)) {
            return dpMap.get(course);
        } else if (!map.containsKey(course)) {
            dpMap.put(course, true);
        } else if (visited.contains(course)) {
            dpMap.put(course, false);
        } else {
            boolean possible = true;
            visited.add(course);
            for (int pre: map.get(course)) {
                if (!couldTake(pre, visited)) {
                    possible = false;
                    break;
                }
            }
            dpMap.put(course, possible);
        }
        return dpMap.get(course);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.dpMap = new HashMap<>();
        this.map = new HashMap<>();

        // create the mapping
        for (int[] pre: prerequisites) {
            int first = pre[0];
            int second = pre[1];
            // take second before first
            this.map.putIfAbsent(first, new HashSet<>());
            this.map.get(first).add(second);
        }

        // calling could take on all course in map:
        for (int course: this.map.keySet()) {
            boolean possible = couldTake(course, new HashSet<>());
            if (!possible) {
                return false;
            }
        }

        return true;
    }
}
```
