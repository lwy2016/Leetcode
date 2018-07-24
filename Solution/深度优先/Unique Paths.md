Description:

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Solution:

动态规划:
自底向上解决
设状态为 f[i][j]， 表示从起点(1, 1)到达(i, j)的路线条数，
则状态转移方程为： f[i][j] = f[i-1][j] + f[i][j-1]

```java
public int uniquePaths(int m, int n) {
    int[] f = new int[n];
    // 第一列都为0
    f[0] = 1; 
    for (int i = 0; i < m; i++) {
        for (int j = 1; j < n; j++) {
            // 左边的 f[j] 表示更新后的 f[j] 与 公式中的 f[i][j]对应
            // 右边的 f[j] 表示老的 f[j] 与公式中的 f[i-1][j]对应
            f[j] = f[j] + f[j - 1];
        }
    }
    return f[n - 1];
}
```