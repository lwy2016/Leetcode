Description:

Given two sorted integer arrays A and B, merge B into A as one sorted array.

Example
A = [1, 2, 3, 0, 0], m = 3, B = [4, 5], n = 3

After merge, A will be filled as [1, 2, 3, 4, 5]

Note
You may assume that A has enough space (size that is greater or equal to m + n)
to hold additional elements from B.
The number of elements initialized in A and B are m and n respectively.

Solution:

```java
    public void merge(int[] nums1, int m, int[] nums2, int n) { 
        // 归并
        int a = m - 1, b = n - 1, curr = m + n - 1;
        while (a >= 0 && b >= 0) {
            nums1[curr--] = nums1[a] >= nums2[b] ? nums1[a--] : nums2[b--];
        }
        while (b >= 0) {
            nums1[curr--] = nums2[b--];
        }
    }
```