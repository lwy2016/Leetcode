有了去哪儿网的offer之后，身心轻松了许多，接下来最主要的是帮助女朋友找到北京的不错的工作，然后完成论文写作，加强基础学习。
自己感兴趣的方向是，算法强化学习、spring cloud微服务、redis。 接下来半年，针对这三方面的知识，进行深入的学习与研究。






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


用一个辅助数组dp[]记录跳到当前位置所需要的最小花费值，长度为cost.length+1
对于f[0]，爬上楼梯的最小体力就是它本身cost[0]
对于f[1]，爬上楼梯的最小体力就是它本身cost[1]
对于f[i]，就是f[i-1]，f[i-2]的较小者与cost[i]的和
所以动态规划方程为: f[i]=min(f[i-1], f[i-2])+cost[i]

```java 
public int mostCostClimbingStairs(int[] cost) {
	if (cost == null || cost.length == 0) return 0;

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



# DynamicProgramming-Medium

221. Maximal Square

338. Counting Bits
413. Arithmetic Slices
647. Palindromic Substrings
712. Minimum ASCII Delete Sum for Two Strings
646. Maximum Length of Pair Chain
650. 2 Keys Keyboard




## 221. Maximal Square
Description:

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

Solution:
二维数组中，包含1的所能围成的最大正方形的面积

新建一个辅助数组dp[][] = new int[][]原数组大小，dp[i][j]用来记录该点左上位置符合的正方形到该点形成的边长增1的正方形的边长。
如果该点为0，那么dp[i][j]=0,如果该点为1，那么dp[i][j]=他的左，右，左上三者的最小值+1。
使用一个变量max记录遍历过程中，出现的最大的边长值，即为最大正方形的边长。

```java
public int maximalSquare(char[][] matrix) {
	if (maxtrix == null || maxtrix.length == 0) return 0;

	int[][] dp = new int[matrix.length][matrix[0].length];
	int max = 0;

	for (int i = 0; i < matrix.length; i++) {
		for (int j = 0; j < matrix[0].length; j++) {
			if (matrix[i][j] == '0') {
				dp[i][j] = 0;
			} else if (i == 0 || j == 0) { // 特殊处理原matrix数组的第一行，第一列
				dp[i][j] = 1;
			} else {
				dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
			}
			max = Math.max(max, dp[i][j]); // 省去一次双循环遍历
		}
	}

	return max * max;
}
```




# DynamicProgramming-Hard