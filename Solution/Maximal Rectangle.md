Description:

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

>1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0 

Return 6.

Solution:

此题可以基于上一题 [Larget Rectangle in Histogram](https://github.com/lwy2016/Leetcode/blob/master/Solution/Largest%20Rectangle%20in%20Histogram.md) 来做，从第一行起，每往下累加一行，产生一次height[]数组，

height[0] = [1, 0, 1, 0, 0];
height[1] = [2, 0, 2, 1, 1];
height[2] = [3, 1, 3, 2, 2];
height[3] = [4, 0, 0, 3, 0];


```java
public int maximalRectangle(char[][] matrix) {
    int row = matrix.length;
    if(row == 0) {
        return 0;
    }
    int column = matrix[0].length;
    int res = 0;
    
    int[] height = new int[column];    // 每次循环，产生一个关于直方图的height[]数组
    
    for( int i = 0; i < row; i++){
        for ( int j = 0; j < column; j++ ){
            height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;   // ==1，height+1,否则置为0
        }
        res = Math.max(res, largestRectangleArea(height));  // 计算该次 height[] 中面积的最大值
    }
    
    return res;
}

public static int largestRectangleArea(int[] height) {
    
    int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
    int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
    lessFromRight[height.length - 1] = height.length;   // the last index 的右侧的序号
    lessFromLeft[0] = -1;                               // the first index 的左侧的序号

    for (int i = 1; i < height.length; i++) {  // 6个元素，循环5次比较,从第二个元素往后比较
        // the current index is i 
        int p = i - 1;                  

        while (p >= 0 && height[p] >= height[i]) {   // 也可以为 p--; 这样做的好处是当有多个相邻的相同值时，可以跳过这些比较，直接得到结果
            p = lessFromLeft[p];        
        }
        // 1.当height[i-1]<height[i]，即左侧小于右侧值时，left[i] = 当前序号-1
        // 2.当height[i-1]>=height[i]时，即左侧大于右侧值时，说明左侧的数值高，p值需要向左回溯，直到所在的序号表示的值比当前序号i表示的值小，记下所在的序号给p
        lessFromLeft[i] = p;   
    }
    System.out.println();
    for (int i = height.length - 2; i >= 0; i--) {  // 6个元素，循环5次比较，从倒数第二个元素往前比较
        // the current index is i 
        int p = i + 1;

        while (p < height.length && height[p] >= height[i]) {   // 也可以为 p++;
            p = lessFromRight[p];
        }
        lessFromRight[i] = p;
    }
    
    int maxArea = 0;
    for (int i = 0; i < height.length; i++) {
        maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
    }

    return maxArea;
}
```