Description:

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5


Solution:

使用归并排序的思想，挨个比较两个数组的值，取小的，最后分奇偶长度返回平均值或者中位值

```
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return -0.0;

    int len1 = nums1 == null ? 0 : nums1.length;
    int len2 = nums2 == null ? 0 : nums2.length;
    int len = len1 + len2;
    
    int index1 = 0, index2 = 0, index = 0;
    int[] nums = new int[len];
    
    while (index1 < len1 && index2 < len2) {
        if (nums1[index1] < nums2[index2]) {
            nums[index++] = nums1[index1++];
        } else {
            nums[index++] = nums2[index2++];
        }
    }
    while (index1 < len1) {
        nums[index++] = nums1[index1++];
    }
    while (index2 < len2) {
        nums[index++] = nums2[index2++];
    }
    
    if (len % 2 == 0) {
        return (nums[len / 2] + nums[(len - 1) / 2]) / 2.0;
    } else {
        return nums[len / 2];
    }

}
```