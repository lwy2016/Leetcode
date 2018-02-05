Description:

Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Solution:

```java
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return result;
        
        int lowBound = -1, upBound = nums.length;
        // 二分法求左边界
        while (lowBound + 1 < upBound) {
            int mid = lowBound + (upBound - lowBound) / 2;
            // 当中点小于target,lowBound移向中点，否则upBound移向中点（若中点==target时，upBound移向中点），
            if (nums[mid] < target) {
                lowBound = mid;
            } else {
                upBound = mid;
            }
        }
        // 循环后得到的lowBound为左边界的值
        // 判断起点是否等于target,如果是赋值给result[0]
        if (lowBound + 1 < nums.length && nums[lowBound + 1] == target) {
            result[0] = lowBound + 1;
        } else {
            return result;
        }
        
        // 二分法求右边界
        upBound = nums.length;
        while (lowBound + 1 < upBound) {
            int mid = lowBound + (upBound - lowBound) / 2;
            if (nums[mid] > target) {
                upBound = mid;
            } else {
                lowBound = mid;
            }
        }
        // 分 lower/upper bound 两次搜索，注意如果在 lower bound 阶段未找到目标值时，upper bound 也一定找不到。
        result[1] = upBound - 1;
         
        return result;
    }
```