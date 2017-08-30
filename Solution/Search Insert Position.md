Description:

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.

```
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
```

Solution:

这么low的代码就Accepted了，
```java
public int searchInsert(int[] nums, int target) {
    for(int i = 0; i < nums.length; i++){
        if(nums[i] >= target){
            return i;
        } 
    }
    return nums.length;
}
```
难以置信，时间复杂度击败了18.7%的人，毕竟是暴力搜索，于是查找前人的思路，用二分查找，可以找的更快
```java
public int searchInsert(int[] nums, int target){
    int start = 0, end = nums.length - 1;
    while(start <= end){
        int mid = start + (end - start)/2;
        if(target == nums[mid]){
            return mid;
        } else if(target < nums[mid]){
            end = mid - 1;
        } else {
            end = mid + 1;
        }
    }
    return start;
}
```
呵呵，二分查找之后，只击败了3.58%的人，

