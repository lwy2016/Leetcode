Description:

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.

Solution:

上一题，第一列(行)全部是1，但是在这一题中不同，第一列如果某一行有障碍物，那么后面的全为0.
```java
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 起点或者终点为1，则为0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[0][0] == 1) return 0;
        
        int[] f = new int[n];
        f[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[0] = f[0] == 0 ? 0 : (obstacleGrid[i][0] == 1 ? 0 : 1);
                f[j] = obstacleGrid[i][j] == 1 ? 0 : (f[j] + f[j - 1]);
            }
        }
        return f[n - 1];
    }
```