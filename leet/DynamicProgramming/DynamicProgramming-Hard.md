有了去哪儿网的offer之后，身心轻松了许多，接下来最主要的是帮助女朋友找到北京的不错的工作，然后完成论文写作，加强基础学习。
自己感兴趣的方向是，算法强化学习、spring cloud微服务、redis。 接下来半年，针对这三方面的知识，进行深入的学习与研究。



# DynamicProgramming-Hard
| Index | Title |
| ----- | :---: |
|312|Burst Balloons|


## 312. Burst Balloons
Description:
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:
```
Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
```

Solution:
扎破气球，与两边的值的乘积之和，求能得到的最多的金币，
像这种求极值问题，一般考虑用动态规划来做
将原数组左右两边填充1，原数组的索引范围就变为[1,n]
dp[i][j]表示(i,j)之间的金币最大值，二维数组只使用到了右上半三角形区域，最终要返回的值是dp[1][n]
i-1  i  i+1 ... k-1 k k+1 ... j-1 j j+1
     dp[i][k-1]   k是最后  dp[k+1][j]
求dp[i][j]的是一个比较麻烦的过程，变量k在i-j之间滑动，找到dp[i][j]的最大值    
可以得到动态规划方程：
dp[i][j]=max(dp[i][j], dp[i][k-1] + nums[i-1]* nums[k]* nums[j+1] + dp[k+1][j])  (i<=k<=j)

```java
public int maxCoins(int[] nums) {
    int n = nums.length;
    int[] arr = new int[n + 2];
    arr[0] = 1;
    arr[n + 1] = 1;
    for (int i = 1; i <= n; i++) {
        arr[i] = nums[i - 1];
    }
    int[][] dp = new int[n + 2][n + 2];
    
    for (int len = 1; len <= n; len++) { // 长度为len的子串
        for (int i = 1; i <= n - len + 1; i++) { // 子串长度为len,i的起始位置区间
            int j = i + len - 1;  // 子串的结束位置
            for (int k = i; k <= j; k++) {
                dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + arr[i - 1] * arr[k] * arr[j + 1] + dp[k + 1][j]);   
            }
        }
    }
    
    return dp[1][n];
}
```