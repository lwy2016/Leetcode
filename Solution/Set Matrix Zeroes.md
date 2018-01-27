Description:

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Solution:

O(m + n)空间的方法很简单，设置两个boolean数组，记录每行和每列是否存在0
想要常数空间，可以复用第一行和第一列

O(m + n)
```java
public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    
    boolean[] row = new boolean[m];
    boolean[] col = new boolean[n];
    
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {
                row[i] = col[j] = true;
            }
        }
    }
    
    for (int i = 0; i < m; i++) {
        if (row[i]) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    for (int j = 0; j < n; j++) {
        if (col[j]) {
            for (int i = 0; i < m; i++) {
                matrix[i][j] = 0;
            }
        }
    }
}
```

O(1)
```java
public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    
    boolean row_has_zero = false;   // 原数组第一行是否存在0
    boolean col_has_zero = false;   // 原数组第一列是否存在0
    
    // 标识第一行，第一列中是否包含0
    for (int j = 0; j < n; j++) {
        if (matrix[0][j] == 0) {
            row_has_zero = true;
            break;
        }
    }
    for (int i = 0; i < m; i++) {
        if (matrix[i][0] == 0) {
            col_has_zero = true;
            break;
        }
    }
    
    // 从剩下的数组中，找出等于0的点，并将对应的第一行，第一列对应的行列位置 位置0
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[0][j] = 0;  
                matrix[i][0] = 0;
            }
        }
    }
    // 从剩下的数组中，查看第一行或第一列的位置是否为0，如果是，将该行或该列全部置为0，
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    
    // 如果第一行，或第一列中包含0，那么将第一行或第一列都 置为0
    if (row_has_zero) {
        for (int j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
    if (col_has_zero) {
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
}
```
