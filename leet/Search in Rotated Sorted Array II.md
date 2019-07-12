Description:

Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.

Solution:

在A[start] == A[mid]时无法确定有序数组，此时只能依次递增start/递减end以缩小搜索范围，时间复杂度最差变为O(n)
在A[start] == A[mid]时递增start序号即可

```java
    public boolean search(int[] nums, int target) { 
        if (nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length - 1;
        
        while (start <= end ) {
            int mid = (end - start) / 2 + start;
            
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[start]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < nums[start]) {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                start++;
            }
        }
        
        return false;
    }
```
