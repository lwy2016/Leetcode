Description:

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

Solution:

```java
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0) return res;
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        int left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (left <= right && top <= bottom) {
            // left 2 right
            for (int i = left; i <= right; i++) res.add(matrix[top][i]);
            top++;
            // top 2 bottom
            for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;
            // right 2 left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
            }
            bottom--;
            // bottom 2 top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }
```
