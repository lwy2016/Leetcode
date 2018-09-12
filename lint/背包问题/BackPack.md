## 1. 单次选择 + 最大体积

Description:

Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Challenge
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.

Notice
You can not divide any item into small pieces.


Solution:

f[i]表示书包空间为 i 的时候能装的A物品的最大容量。
两层循环，外层遍历数组A， 内层反向遍历数组f[j](m >= j > 0)
若背包容量j大于物品体积A[i], 则取前 i-1 次循环求得的最大容量f[i](即存不下A[i]了) 和 背包体积为 j-A[i]时的最大容量f[j-A[i]]与第i个物品体积A[i]之和(即) 两者的最大值为 f[i]

```java
public int backPackI(int m, int[] A) {
	int[] f = new int[m + 1];

	for (int i = 0; i < A.length; i++) {
		for (int j = m; j > 0; j--) {
			if (j >= A[i]) {
				f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
			}
		}
	}

	return f[m];
}
```

## 2. 单次选择 + 最大价值 (01背包)

Given n items with size A[i] and value V[i], and a backpack with size m. What's the maximum value can you put into the backpack?

Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Challenge
O(n x m) memory is acceptable, can you do it in O(m) memory?

Solution:

f[j]取的是价值较大值，而非体积最大值。所以只要把 f[j-A[i]]+A[i] 换成 f[j-A[i]]+V[i]就可以了

```java
public int backPackII(int m, int[] A, int[] V) {
	int[] f = new int[m + 1];

	for (int i = 0; i < A.length; i++) {
		for (int j = m; j > 0; j--) {
			if (f[j] >= A[i]) {
				f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
			}
		}
	}

	return f[m];
}
```

## 3. 重复选择 + 最大价值 (完全背包)

Given n kind of items with size Ai and value Vi( each item has an infinite number available) and a backpack with size m. What's the maximum value can you put into the backpack?

Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.

f[j]取的是价值较大值, 内层循环顺序遍历f[j](1<=j<=m)

```java
public int backPackIII(int m, int[] A, int[] V) {
	int[] f = new int[m + 1];

	for (int i = 0; i < A.length; i++) {
		for (int j = 1; j <= m; j++) {
			if (j >= A[i]) {
				f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
			}
		}
	}

	return f[m];
}
```

## 4. 重复选择 + 唯一排列 + 装满可能性总数

Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.

Each item may be chosen unlimited number of times

Example
Given candidate items [2,3,6,7] and target 7,

A solution set is:

[7]
[2, 2, 3]
return 2


```java 
public int backpackIV(int[] nums, int target) {
	int[] f = new int[target + 1];
	f[0] = 1;

	for (int i = 0; i < nums.length; i++) {
		for (int j = 1; j <= target; j++) {
			if (j == nums[i]) f[j]++; 
			else if (j > nums[i]) f[j] = f[j] + f[j - nums[i]];
		}
	}

	return f[target];
}
```

## 5. 单次选择 + 装满可能性总数

Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.

Each item may only be used once

Example
Given candidate items [1,2,3,3,7] and target 7,

A solution set is:

[7]
[1, 3, 3]
return 2

```java 
public int backPackV(int[] nums, int target) {
	int[] f = new int[target + 1];
	f[0] = 1;

	for (int i = 0; i < nums.length; i++) {
		for (int j = target; j > 0; j--) {
			if (j >= nums[i]) {
				f[j] = f[j] + f[j - nums[i]];
			}
		}
	}
}
```

## 6. 重复选择 + 不同排列 + 重复可能性总数

Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Notice
The different sequences are counted as different combinations.

Example
Given nums = [1, 2, 4], target = 4

The possible combination ways are:

[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
return 6

Solution:

target 从1开始逐步向后求可能的组合数，f[i]指target为i时的组合数
i 能拆分成 nums[j]和i - nums[j]的值

```java 
public int backPackVI(int[] nums, int target) {
	int[] f = new int[target + 1];
	f[0] = 1;

	for (int i = 1; i <= target; i++) {
		for (int j = 0; j < nums.length; j++) {
			if (i >= nums[j]) {
				f[i] = f[i] + f[i - nums[j]];
			}
		}
	}

	return f[target];
}
```