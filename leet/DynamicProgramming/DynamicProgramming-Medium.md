# DynamicProgramming-Medium

- 338. Counting Bits
- 647. Palindromic Substrings
- 413. Arithmetic Slices
- 712. Minimum ASCII Delete Sum for Two Strings

- 646. Maximum Length of Pair Chain
- 343. Integer Break
- 650. 2 Keys Keyboard


- 221. Maximal Square


## 338. Counting Bits
Description:

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n * sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __ builtin_popcount in c++ or in any other language.

Solution:
```
0	0000	0

1 	0001	1

2	0010	1
3 	0011	2

4	0100	1
5	0101	2
6	0110	2
7	0111	3
	
8	1000	1
9	1001	2
10	1010	2
11	1011	3
12	1100	2
13	1101	3
14	1110	3
15 	1111	4
```
发现下面这个规律，确实不是一件容易的事情。
如果i是偶数，则1出现次数等于它右移一位的值
如果i是奇数，则1出现次数等于它右移一位的值+1 
状态转移方程： dp[i]=dp[i>>1]+(i&1)

```java
public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        
        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i>>1] + (i & 1);
        }
        
        return dp;
    }
```


## 647. Palindromic Substrings
Description:

Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.

Solution:
辅助数组dp[i][j]用来记录(j,i)之间是否是回文子串，主要使用到了dp[][]的上三角区域
每判断属于回文，计数res增加一次
1. 当j==i时，这个单字符是回文
当ji相邻即j+1==i，且s[j]==s[i]时，这是一个回文串
上述两种情况可以用j+1>i-1表示
2. 当s[j]==s[i]时，若dp[j+1][i-1]是回文，则dp[j][i]也是回文

```java
public int countSubstrings(String s) {
    if (s == null || s.length() == 0) return 0;
    
    int n = s.length();
    boolean[][] dp = new boolean[n][n];
    int res = 0;
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            if (s.charAt(i) == s.charAt(j) && (j +1 > i - 1 || dp[j + 1][i - 1])) {
                dp[j][i] = true;
                res++;
            }
        }
    }
    
    return res;
}
```

## 413. Arithmetic Slices
Description:

A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:
```
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
```
The following sequence is not arithmetic.
1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.

Example:
A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

Solution:
等差数列 
规定：子数列的长度最少为3，且在原数组中是连续出现的子序列
那么，当连续的等差数列有n个元素的时候，则会有(1+2+...+(n - 2))个等差子数列

输入给定的数组未必所有元素都满足等差数列
当满足nums[i + 1] - nums[i] = nums[i] - nums[i - 1]时：
有动归方程： dp[i]=dp[i-1]+1
res用来统计到当前位置的等差数列的数量

```java
public int numberOfArithmeticSlices(int[] A) {
    if (A == null || A.length < 3) return 0;
    
    int n = A.length;
    int[] dp = new int[n];
    int res = 0;
    
    for (int i = 1; i < n - 1; i++) {
        if (A[i + 1] - A[i] == A[i] - A[i - 1]) {
            dp[i] = dp[i - 1] + 1; 
            res += dp[i];
        } 
    }
    
    return res;
}
```


## 712. Minimum ASCII Delete Sum for Two Strings









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