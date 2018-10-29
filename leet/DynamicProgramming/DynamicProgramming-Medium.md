# DynamicProgramming-Medium

| Index | Title |
| ----- | :---: |
|338|Counting Bits|
|647|Palindromic Substrings|
|413|Arithmetic Slices|
|712|Minimum ASCII Delete Sum for Two Strings|
|646|Maximum Length of Pair Chain|

|343|Integer Break|
|650|2 Keys Keyboard|


|221|Maximal Square|


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
Description:

Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
```
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
```
Example 2:
```
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
```
Note:
0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].

Solution:
求，使两个字符串相等，需要删除的最少的字符的ASCII码值之和
字符串又是求极值的题，想都不要想，直接上DP
建立一个二维数组dp, dp[i][j]表示字符串s1的前i个字符 和 字符串s2的前j个字符 变相等所需要删除的字符的最小ASCII码的累加值。
1. 初始化边缘，即有一个字符串为空的话，那么另一个字符串有多少字符就要删除多少字符，才能变为空字符串
2. 遍历二维数组的每一个位置，dp[i][j]当对应位置字符相等时，那么可直接赋上一个的值，即dp[i-1][j-1]
当不相等时，可以删除s1的字符，且加上被删除ASCII码的值，即dp[i-1][j]+s1(i-1), 也可以删除s2的字符，且加上...，即dp[i][j-1]+s2(j-1)
即dp[i][j]=min(dp[i-1][j]+s1(i-1), dp[i][j-1]+s2(j-1))

```java
public int minimumDeleteSum(String s1, String s2) {
	int len1 = s1.length();
	int len2 = s2.length();

	int[][] dp = new int[len1 + 1][len2 + 1];

	for (int i = 1; i <= len1; i++) {
		dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
	}
	for (int j = 1; j <= len2; j++) {
		dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
	}

	for (int i = 1; i <= len1; i++) {
        for (int j = 1; j <= len2; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
            }
        }
    }

	return dp[len1][len2];
}
```


## 646. Maximum Length of Pair Chain
Description:

You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].

Solution:
首先要对数组对进行排序，这里使用到了JDK8的语法 (a,b)->(a[0]-b[0])进行了快速的排序，不然另一种重写compare()方法很麻烦
dp[i]存储i之前的链表长度的最大值，若arr[j][1]< arr[i][0]，那么dp[i]就可在dp[j]的基础上+1
每次计算dp[i]的值时，需要与i前面所有的pairs进行比较，并用max来保存计算过程中符合条件的最大的数组对数量

```java
public int findLongestChain(int[][] pairs) {
	Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));

	int[] dp = new int[pairs.length];
	int max = 0;

	for (int i = 1; i < pairs.length; i++) {
		for (int j = 0; j < i; j++) { // 逆序的话，就求不出来正确的值了, 因为dp[i]定义的是存储i之前的链表长度的最大值
			if (pairs[j][1] < pairs[i][0]) {
				dp[i] = dp[j] + 1;
			}
			max = Math.max(max, dp[i]);
		} 
	}

	return max + 1; // 将它自身算上
}
```


## 343. Integer Break



## 650. 2 Keys Keyboard
Description:

Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Note:
The n will be in the range [1, 1000].

Solution:
给了复制粘贴两个键，给了一个A，目标是利用这两个键打印出n个A，复制的时候全部复制，问，打印n个A需要操作多少步
这种有明显的递推特征的题，一定要尝试DP，
找规律最重要，DP要找出递推公式，如果无法发现内在的联系，递推公式就比较难写出来了，
从简单的例子开始分析，试图找到规律：
当n=1，已经有一个A了，不需要其他操作，返回0
当n=2，复制一次，粘贴一次，返回2
当n=3，复制一次，粘贴2次，返回3
当n=4，复制一次，粘贴3次，返回4 或 复制一次，粘贴一次，复制一次，粘贴一次 返回4
当n=5，复制一次，粘贴4次，返回5
当n=6，复制一次，粘贴2次，即AAA，复制一次，粘贴一次，返回5 或 复制一次，粘贴一次，复制一次，粘贴2次，返回5
对于任意一个n,最差的情况不会多于n步，但有可能小于n步，对于n=6,两种都是5次，2* 3，或3* 2, 要找到所有的因子，这个因子可以当做模块的个数，
动归方程：在i能整除j的情况下， dp[i]=dp[j]+dp[i/j]

```java
public int minSteps(int n) {
    int[] dp = new int[n + 1];
    
    for (int i = 2; i <= n; i++) {
        dp[i] = i;
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                dp[i] = dp[j] + dp[i / j];
            }
        }
    }
    
    return dp[n];
}
```







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










