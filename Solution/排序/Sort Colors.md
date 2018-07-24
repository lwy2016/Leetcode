Description:

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

Solution:

不能扫描2遍数组

counting sort 查询出各自出现的次数，重构数组 O(n), O(1)
```java
    public void sortColors(int[] nums) {
        int[] counts = new int[3];

        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]]++;
        }

        for (int i = 0, index = 0; i < 3; i++) {
            for (int j = 0; j < counts[i]; j++) {
                nums[index++] = i;
            }
        }
    }
```

双指针  O(n), O(1)
```java
    public void sortColors(int[] nums) {
        // 一个是 red 的 index, 一个是 blue 的 index, 两边往中间走
        int red = 0, blue = nums.length - 1;
        
        for (int i = 0; i < blue + 1;) {
            if (nums[i] == 0) {
                swap(i++, red++, nums);
            } else if (nums[i] == 2) {
                swap(i, blue--, nums);
            } else {
                i++;
            }
        }
    }
    
    public void swap(int x, int y, int[] nums) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
```