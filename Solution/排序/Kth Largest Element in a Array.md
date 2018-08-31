Description:

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Solution:

快排，从大到小排序，未必全部都排列完，基准元素左侧的元素都大于基准值
```java
public int findKthLargest(int[] nums, int k) {
	int left = 0, right = nums.length - 1;

	while (true) {
		int pivot = helper(nums, left, right);

		if (pivot == k - 1) return nums[pivot];
		else if (pivot > k - 1) right = pivot - 1;
		else left = pivot + 1;
	}
}

public int helper(int[] nums, int left, int right) {
	int pivot_value = nums[left];

	int l = left + 1;
	int r = right;

	while (l <= r) {
		while (l <= r && nums[l] > pivot_value) l++;
		while (l <= r && nums[r] <= pivot_value) r--;

		if (l > r) break;
		int temp = nums[l];
		nums[l] = nums[r];
		nums[r] = temp;
	}
	int t = nums[left];
	nums[left] = nums[r];
	nums[r] = t;

	return r;
}
```