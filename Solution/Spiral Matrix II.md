Description:

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

Solution:

```java
    public int[][] generateMatrix(int n) {
        int[][] board = new int[n][n];
        if (n == 0) return board;
        int count = 1;
        
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            // left 2 right
            for (int i = left; i <= right; i++) board[top][i] = count++;
            top++;
            // top 2 bottom 
            for (int i = top; i <= bottom; i++) board[i][right] = count++;
            right--;
            // right 2 left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) board[bottom][i] = count++;
            }
            bottom--;
            // bottom 2 top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) board[i][left] = count++;
            }
            left++;
        }
        
        return board;
    }
```