# DynamicProgramming-Easy

121. Best Time to Buy and Sell Stock
746. Min Cost Climbing Stairs
70.  Climbing Stairs
53.  Maximum Subarray
198. House Robber
303. Range Sum Query - Immutable


## 121. Best Time to Buy and Sell Stock
Description:

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
  
Note that you cannot sell a stock before you buy one.

Example 1:
```
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
```
```
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```

Solution:
买卖股票，只买卖一次，能获取的最大利益

1. 用一个辅助数组dp[]记录当前的最大利益，minPrice记录遍历过程中的最小值，动归方程： dp[i]=min(dp[i-1],minPrice-prices[i])

```java
public int maxProfit(int[] prices) {
	if (prices == null || prices.length < 2) return 0;

	int[] dp = new int[prices.length];
	int minPrice = prices[0];

	for (int i = 1; i < prices.length; i++) {
		if (prices[i] < minPrice) {
			minPrice = prices[i];
		} else {
			dp[i] = Math.min(dp[i - 1], prices[i] - minPrice);
		}
	}

	return dp[prices.length - 1];
}
```


2. 记录遍历过程中的最小值，取 当前值与最小值之差 的最大值
与1.比较，数组缩减为一个变量 minPrice

```java
public int maxProfit(int[] prices) {
	if (prices == null || prices.length < 2) return 0;

	int minPrice = prices[0];
	int max = 0;

	for (int i = 0; i < prices.length; i++) {
		if (prices[i] < minPrice) {
			minPrice = prices[i];
		} else {
			max = Math.max(max, prices[i] - minPrice);
		}
	}

	return max;
}
```


## 746. Min Cost Climbing Stairs
Description:

On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
```
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
```
```
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
```
Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

Solution:
爬楼梯，每次爬一个台阶或二个台阶，爬到每个台阶有一定的花费值，求爬完所有台阶用的最小的花费是多少

1. 用一个辅助数组dp[]记录跳到当前位置所需要的最小花费值，长度为cost.length+1
对于f[0]，爬上楼梯的最小体力就是它本身cost[0]
对于f[1]，爬上楼梯的最小体力就是它本身cost[1]
对于f[i]，就是f[i-1]，f[i-2]的较小者与cost[i]的和
所以动态规划方程为: f[i]=min(f[i-1], f[i-2])+cost[i]

```java 
public int minCostClimbingStairs(int[] cost) {
	if (cost == null || cost.length < 2) return 0;

	int[] f = new int[cost.length + 1];
	f[0] = cost[0];
	f[1] = cost[1];

	for (int i = 2; i <= cost.length; i++) {
		f[i] = Math.min(f[i - 1], f[i - 2]) + (i == cost.length ? 0 : cost[i]);
	}

	return f[cost.length];
}
```
cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
则dp = [1, 100, 2, 3, 3, 103, 4, 5, 104, 6, 6]


## 70.  Climbing Stairs
Description:

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Solution:
这是斐波那契数列，求法有几种
1,2,3,5,8,13,21,34...

1. 递归

```java
public int climbStairs(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;
    
    return climbStairs(n - 1) + climbStairs(n - 2);
}
```
在 n = 44 的时候 就 Time Limit Exceeded 超时了

2. 迭代
两个指针prev代表前一个索引, curr代表当前索引

```java
public int climbStairs(int n) {
	if (n == 1) return 1;
	if (n == 2) return 2;

	int prev = 1, curr = 2;
	for (int i = 2; i < n; i++) {
		int temp = prev;  // 需使用一个临时变量来帮助
		prev = curr;
		curr = temp + curr;
	}

	return curr;
}
```

3. 动归
使用一个辅助数组，记录所有的结果值

```java
public int climbStairs(int n) {
	if (n == 1) return 1;
	if (n == 2) return 2;

	int[] dp = new int[n];
	dp[0] = 1;
	dp[1] = 2;

	for (int i = 2; i < n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}

	return dp[n - 1];
}
```


## 53.  Maximum Subarray
Description:

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
```
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Solution:
求最大连续子数组之和

1. 动归
dp[i]表示从开始到当前位置的最大连续子数组之和
动归方程： dp[i]=max(dp[i-1]+nums[i], nums[i])
而最大值就存在于dp[]数组中

```java
public int maxSubArray(int[] nums) {
	if (nums == null || nums.length == 0) return 0;

    int n = nums.length;
	int[] dp = new int[n];
	dp[0] = nums[0];
	int max = nums[0];

	for (int i = 1; i < n; i++) {
		dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
		max = Math.max(max, dp[i]);
	}

	return max;
}
```

2. 动归-数组缩减为一个变量
基于1. 可以看出只使用了dp[i],dp[i-1],没有使用到dp[i-2]，因此可以将辅助数组变为一个辅助变量

```java
public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    
    int n = nums.length;
    int dp = 0;
    int max = nums[0];
    
    for (int i = 0; i < n; i++) {
        dp = Math.max(dp + nums[i], nums[i]);
        max = Math.max(max, dp);
    }
    
    return max;
}
```

3. 迭代
基于2. 的单变量，可实现额外功能，记录最大子串的开始，结束位置

```java
public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    
    int n = nums.length;
    int dp = 0;
    int max = nums[0];
    int start = 0, end = n - 1;
    
    for (int i = 0; i < n; i++) {
        if (dp + nums[i] > nums[i]) {
            dp = dp + nums[i];
            if (dp > max) {
                end = i;
            }
        } else {
            dp = nums[i];
            if (dp > max) {
                start = i;
            }
        }
        
        max = Math.max(max, dp);
    } 
    System.out.println(start + "--" + end);
    
    return max;
}
```


## 198. House Robber
Description:

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
```
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
```
```
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
```

Solution:

1. 动归
当前位置存储当前抢劫的最大值
当前位置的值为该位置的值与前第二个之和 与 前一个的最大值
状态方程为：f[i] = max(f[i - 2] + nums[i], f[i - 1])

```java
public int rob(int[] nums) {
	if (nums == null || nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];

    int n = nums.length;
    int[] dp = new int[n];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);  // 当前抢劫的最大值

	for (int i = 2; i < n; i++) {
        dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    }

    return dp[n - 1];
}
```


## 303. Range Sum Query - Immutable
Description:

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

Solution:
查找某一范围(i,j)数组之和，需多次查询，那么就不能查询一次计算一次，而应该将所有的(i,j)之和存储在一个辅助数组中

1. 二维动态--超时
使用二维数组，依次保存i->j范围内的和，二维数组使用的是上三角区域，且主对角线存的值一次是nums[i]的值
状态方程为：dp[i][j]=dp[i][j - 1] + nums[j]
时间复杂度->O(n^2) 提交后超时

```java
class NumArray {
    int[][] dp;

    public NumArray(int[] nums) {
        int n = nums.length;
        dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + nums[j];
            }
        }
    }
    
    public int sumRange(int i, int j) {
        return dp[i][j];
    }
}
```

2. 一维动归
将1. 的二维动归方程降维(1. 中的i没有发生变化，因此将它去掉)，变成 dp[i]=dp[i-1]+nums[i]，观察这个动归方程，
dp[i]表示的就是依次保存(0,i)之间数组之和，若要求(i,j)之间的值，就等于是求dp[j]-dp[i]+nums[i]的值

```java
class NumArray {
    int[] dp;
    int[] arr; // 用来复制 nums[]

    public NumArray(int[] nums) {
        int n = nums.length;
        dp = new int[n];
        dp[0] = nums[0];
        arr = Arrays.copyOf(nums, n);
        
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        return dp[j] - dp[i] + arr[i];
    }
}
```