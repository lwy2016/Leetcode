Description:

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
[[1,3,1],
 [1,5,1],
 [4,2,1]]
Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.

Solution:

设状态为 f[i][j] 表示从起点(0, 0) 到达(i, j)的最小路径和
则状态转移方程为： f[i][j] = min(f[i-1][j], f[i][j-1]) + grid[i][j]

滚动数组：
```java
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] f = new int[n];
        f[0] = grid[0][0];
        // 初始值 是 Integer.MAX_VALUE 因为后面用了min函数
        for (int i = 1; i < n; i++) {
            f[j] = grid[0][j] + f[j - 1];
        }
        for (int i = 0; i < m; i++) {
            f[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                f[j] = Math.min(f[j], f[j - 1]) + grid[i][j];
            }
        }
        return f[n - 1];
    }
```

二维数组：
```java
pubic int minPathSum(int[][] grid) {
    if (grid.length == 0) return 0;
    int m = grid.length;
    int n = grid[0].length;

    int[][] f = new int[m][n];
    f[0][0] = grid[0][0];
    
    for (int i = 1; i < m; i++) {
        f[i][0] = f[i - 1][0] + grid[i][0];
    }
    for (int i = 1; i < n; i++) {
        f[0][i] = f[0][i - 1] + grid[0][i];
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
        }
    }
    return f[m - 1][n - 1];
}
```
