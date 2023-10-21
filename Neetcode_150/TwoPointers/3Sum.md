Problem: [3Sum](https://leetcode.com/problems/3sum/)

<strong>Solution</strong>
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if ((i == 0) || (nums[i] != nums[i - 1])) { // also skip through the values already checked
                // run two sum 
                int low = i + 1;
                int high = nums.length - 1;
                int target = -nums[i];
                while (low < high) {
                    int sum = nums[low] + nums[high];
                    if (sum < target) {
                        low ++;
                    } else if (sum > target) {
                        high -- ;
                    } else {
                        result.add(new ArrayList<>(Arrays.asList(nums[i], nums[low], nums[high])));
                        low ++;
                        high --;

                        // also avoid repeating results
                        while (low < nums.length && nums[low] == nums[low - 1]) {
                            low++;
                        }
                        while (high >= 0 && nums[high] == nums[high + 1]) {
                            high --;
                        }
                    }
                }
            }
        }

        return result;
    }
}
```
