Problem: [Maximum Distance in Arrays](https://leetcode.com/problems/maximum-distance-in-arrays/description/)



<strong>Approach 1</strong>
```c++
class Solution {
public:
    int maxDistance(vector<vector<int>>& arrays) {
        // implement second approach
        int globalMaxIdx = 0;
        int globalMinIdx = 0;
        int result = 0;
        for (int i = 0; i < arrays.size(); i++) {
            vector<int> localArr = arrays[i];
            // std::cout << localArr.size() << std::endl;
            int length = localArr.size();

            if (localArr[0] < arrays[globalMinIdx][0]) {
                globalMinIdx = i;
            }

            if (localArr[length - 1] > arrays[globalMaxIdx][arrays[globalMaxIdx].size() - 1]) {
                globalMaxIdx = i;
            }
        }

        // std::cout << "Max idx: " << globalMaxIdx << std::endl;
        // std::cout << "Min idx: " << globalMinIdx << std::endl;

        if (globalMinIdx != globalMaxIdx) {
            return arrays[globalMaxIdx][arrays[globalMaxIdx].size() - 1] - arrays[globalMinIdx][0];
        }

        for (int i = 0; i < arrays.size(); i++) {
            vector<int> localArr = arrays[i];
            int length = localArr.size();
            if (globalMaxIdx != i) {
                int localResult = localArr[length - 1] - arrays[globalMinIdx][0];
                if (localResult > result) {
                    result = localResult;
                }
            }

            if (globalMinIdx != i) {
                int localResult = arrays[globalMaxIdx][arrays[globalMaxIdx].size() - 1] - localArr[0];
                if (localResult > result) {
                    result = localResult;
                }
            }
        }
        return result;
    }
};


/*
    Can determine the max distance by retrieving the max value of one array and deduct it with the min value of another array

    If the max and min are from different array => optimal solution
    - if not:
        + replace max and check
            - run replace max by finding an alternative?
        + replace min and check
            - run replace min by finding an alternative?

    So we need to store 2 max and 2 mins along with the array index storing them?

    

    2nd approach:
        - store the index of global max and global min
        - for each array we traverse through:
            - if the index of global max is different:
                check: max of that array - global min and store the value in result
            - if the index of global min is different:
                check: global max - local min and store the value in result
*/
```


<strong>Approach 2</strong>
```c++
class Solution {
public:
    int maxDistance(vector<vector<int>>& arrays) {
        int globalMin = arrays[0][0];
        int globalMax = arrays[0][arrays[0].size() - 1];
        int result = 0;

        for (int i = 1; i < arrays.size(); i++) {
            vector<int> localArr = arrays[i];

            int localMin = localArr[0];
            int localMax = localArr[localArr.size() - 1];

            // std::cout << "global max: " << globalMax << std::endl;
            // std::cout << "global min: " << globalMin << std::endl;
            // std::cout << "local max: " << localMax << std::endl;
            // std::cout << "local min: " << localMin << std::endl;

            result = std::max(std::max(result, globalMax - localMin), localMax - globalMin);
            globalMax = std::max(globalMax, localMax);
            globalMin = std::min(globalMin, localMin);
        }

        return result;
    }
};
```
