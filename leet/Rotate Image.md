Description:

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
```
Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```

Example 2:
```
Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```


Solution:

副对角线反转一次，然后水平中线反转一次
```
1  2     4  2     3  1
3  4     3  1     4  2
```

```java
public void rotate(int[][] matrix) {
    int n = matrix[0].length, temp;
    // 副对角线反转
    for(int i = 0; i < n-1; i++){
        for(int j = 0; j < n-i-1; j++){
            temp = matrix[i][j];
            matrix[i][j] = matrix[n-j-1][n-i-1];
            matrix[n-j-1][n-i-1] = temp;
        }
    }
    // 水平中线反转
    for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n; j++){
            temp = matrix[i][j];
            matrix[i][j] = matrix[n-i-1][j];
            matrix[n-i-1][j] = temp;
        }
    }
}
```

从外到内一圈一圈的转,使用一个临时变量，一次变换4个点，

```java
public void rotate(int[][] matrix) {
    int n = matrix[0].length, temp;
    for(int i = 0; i < n / 2; i++){  // 循环次数
        for(int j = i; j < n - 1 - i; j++){  //j= i, j < n - 1 - i  内层
            temp = matrix[i][j];
            matrix[i][j] = matrix[n-1-j][i];
            matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
            matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
            matrix[j][n-1-i] = temp;
        }
    }
}
```
