Description:

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

![rainwatertrap](http://7xnyvm.com1.z0.glb.clouddn.com/rainwatertrap.png)

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Solution:

这道题比较直接的做法类似Longest Palindromic Substring中的第一种方法，对于每一个bar往两边扫描，找到它能承受的最大水量，然后累加起来即可。每次往两边扫的复杂度是O(n)，对于每个bar进行处理，所以复杂度是O(n^2)，空间复杂度是O(1)

优化的算法。这种方法是基于动态规划的，基本思路就是维护一个长度为n的数组，进行两次扫描，一次从左往右，一次从右往左。第一次扫描的时候维护对于每一个bar左边最大的高度是多少，存入数组对应元素中，第二次扫描的时候维护右边最大的高度，并且比较将左边和右边小的最大高度（我们成为瓶颈）存入数组对应元素中。这样两遍扫描之后就可以得到每一个bar能承受的最大水量，从而累加得出结果。这个方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。空间上需要一个长度为n的数组，复杂度是O(n)

```java
int n = height.length;
int[] left = new int[n];
int[] right = new int[n];
int max_left = 0, max_right = 0;
int res = 0;

for (int i = 0; i < n; i++){
	left[i] = max_left;
	if(height[i] > max_left){
		max_left = height[i];
	}
}
for (int j = n - 1; j >= 0; j--){
	right[j] = max_right;
	if(height[j] > max_right){
		max_right = height[j];
	}
}
for (int k = 0; k < n; k++){
	if(left[k] > height[k] && right[k] > height[k]){
		res += (left[k] < right[k]) ? (left[k] - height[k]) : (right[k] - height[k]);
	}
}
return res;
```