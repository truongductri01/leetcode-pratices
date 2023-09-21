Problem: [Time Based Key Value Store](https://leetcode.com/problems/time-based-key-value-store/)

<strong>Understanding:</strong> This is a fascinating problem requiring us to design a new kind of map/database that could retrieve a `key: String` by `timestamp: int`. 

The tricky part of the problem is that it requires us to retrieve data of a key such that the `return timestamp` is less than or equal to the `requested timestamp`.

For example, if we have a key: `foo` with the following timestamp: `2: "a", 4: "b", 6: "c"`. This means that timestamp `2` will be matched with value `a`, `4` be matched with value `b` and `6` be matched with value `c`.

So, if the request is `get(foo, 2)` we can simply return `a`. Similarly `get(foo, 3)` should also return `a` since `2` is already the closest timestamp to `3`. 
And if the request is `get(foo, 5)`, that should return `b`. However, if the request is `get(foo, 1)`, we should return `""` since there is no timestamp available that is smaller than or equal to `1`.

<strong>Matching:</strong> We want to match this problem to a DataStructure or Algorithm that we already know

1. Data Structure: since we want a fast retrieval runtime, HashMap (or dictionary) is the best choice. And in order to retrieve value as `map.get(key).get(timestamp)`, the type of the map should be `Map<String, Map<Integer, String>>`
2. Algorithm: there is an interesting find when we look at the input constraints. The `timestamp` value passed into the `set()` function will be strictly increased. That means if we keep track of the a list of `timestamp`, that list will have a strictly ascending order. This simply remind us of `Binary Search` since we have a sorted array (list).

<strong>Implementation</strong>

We need to have a map of type `Map<String, Map<Integer, String>>` as discussed above. 

But we also need another map of type `Map<String, List<Integer>>`. This map will store a list of `timestamps` connected with a specific `key` in ascending order. This will be used as source data for binary search.

```java
class TimeMap {
    Map<String, Map<Integer, String>> keyValueMap;
    Map<String, List<Integer>> keyTimeMap;
    public TimeMap() {
        this.keyValueMap = new HashMap<>();
        this.keyTimeMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        this.keyValueMap.putIfAbsent(key, new HashMap<>());
        this.keyValueMap.get(key).put(timestamp, value);

        // create a list of time always in ascending order
        this.keyTimeMap.putIfAbsent(key, new ArrayList<>());
        this.keyTimeMap.get(key).add(timestamp);
    }

    private Integer findIndex(String key, int timestamp) {
        // return -1 if we find nothing
        // return i if time at i <= timestamp and time at i + 1 > time 
        // if timestamp < first element in the list => return -1
        // if time stamp >= last element in the list, return the last index 

        // 1. check if the list exist
        if (!this.keyTimeMap.containsKey(key)) {
            return -1;
        }

        // 2. get the list and check the edge case
        List<Integer> timeList = this.keyTimeMap.get(key);
        int left = 0;
        int right = timeList.size() - 1;

        if (timestamp < timeList.get(left)) {
            return -1;
        } else if (timestamp >= timeList.get(right)) {
            return timeList.get(right);
        } else {
            while (left <= right) {
                int middle = (left + right) / 2;

                if (middle <= timeList.size() - 1 && timeList.get(middle) <= timestamp && timeList.get(middle + 1) > timestamp) {
                    return timeList.get(middle);
                } else if (timestamp > timeList.get(middle)) { // lef side is useless
                    left = middle + 1;
                } else if (timestamp < timeList.get(middle)) { // right side is useless
                    right = middle - 1;
                }
            }
            return -1;
        }

    }
    
    public String get(String key, int timestamp) {
        /**
        Utilize binary search to find a place in the middle of the list in which index i <= time and index i + 1 > time. return i;
        */
        // find the index of the current timestamp 
        int timeValue = this.findIndex(key, timestamp);

        if (timeValue < 0) {
            return "";
        } else {
            return this.keyValueMap.get(key).get(timeValue);
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
```

<strong>Explanation:</strong>

The constructor function `TimeMap()` should be easy to understand since we just initialize the maps needed for our implementation. 

Similarly, `set()` method is just putting elements into the map like we discussed above.

The part that might be a little bit confusing is the `findIndex(String key, int timestamp)` function which is trying the find the `time` value that is closest to the requested `timestamp`. 
- if there is no existing `key` stored in our database, return -1. This denoted that there is no valid `time` value. Else, we have a `list` variable storing all timestamps value already get put into the database connecting with the key `key`.
- if the value of `timestamp` is strictly smaller than the first value in `list`, we can also return -1 since there is no other suitable value.
- if the value of `timestamp` is larger than or equal to the last element in `list`, we return that last element since that is already the largest value we could possibly get.
- otherwise, we can start our binary search. The main go is to find an index `i` at which `list.get(i) <= timestamp` and `list.get(i + 1) > timestamp`. This will shows that `list.get(i)` is the maximum time value we can get.

For example, assume that we have a request `findIndex("foo", 5)` and we have an associated list of `timestamp` connecting with key `"foo"` is: [1, 2, 3, 4, 6, 7]. 
- start binary search with `left = 0` and `right = 5`.
- start 1st while: `middle = 2`. `list.get(2) = 3` This value does not satisfy anything and requires us to ignore anything on the left of `middle` (anything smaller than index `2`). So: `left = 3` and start new while loop
- start 2st while with `left = 3, right = 5`: `middle = (3 + 5) / 2 = 4`. `list.get(4) = 6` this also does not satisfy our checks and `6 > 5` so we ignore anything larger than `6`. So: `right = middle - 1 = 3` and continue the while
- start 3rd while with `left = right = 3`: `middle = 3` and `list.get(3) = 4`. This satisfies our check since `list.get(3) = 4 <= 5` and `list.get(3 + 1) = 6 > 5`. So we `return 4`.
Finally, we just have to return `map.get("foo").get(4)` as the final result
