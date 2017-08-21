Description:

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

![图1](http://7xnyvm.com1.z0.glb.clouddn.com/leetCode-84-1.png)

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

![图2](http://7xnyvm.com1.z0.glb.clouddn.com/leetCode-84-2.png)

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.


Solution:
当前序号用 i 表示，如 i = 1, 求与它同一高度的元素序号，其中它的左侧为 -1，右侧为 6，
lessFromLeft[]  = [-1, -1, 1, 2, 1, 4];
lessFromRight[] = [ 1,  6, 4, 4, 6, 6];
面积则为 (lessFromRight[i] - lessFromLeft[i] - 1) * height[i]

```java
public static int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
        return 0;
    }
    int[] lessFromLeft = new int[heights.length]; // idx of the first bar the left that is lower than current
    int[] lessFromRight = new int[heights.length]; // idx of the first bar the right that is lower than current
    lessFromRight[heights.length - 1] = heights.length;   // the last index 的右侧的序号
    lessFromLeft[0] = -1;                               // the first index 的左侧的序号

    for (int i = 1; i < heights.length; i++) {  // 6个元素，循环5次比较,从第二个元素往后比较
        // the current index is i 
        int p = i - 1;                  

        while (p >= 0 && heights[p] >= heights[i]) {   
            p = lessFromLeft[p];   // 也可以为 p--; 这样做的好处是当有多个相邻的相同值时，可以跳过这些比较，直接得到结果
        }
        // 1.当heights[i-1]<heights[i]，即左侧小于右侧值时，left[i] = 当前序号-1
        // 2.当heights[i-1]>=heights[i]时，即左侧大于右侧值时，说明左侧的数值高，p值需要向左回溯，直到所在的序号表示的值比当前序号i表示的值小，记下所在的序号给p
        lessFromLeft[i] = p;   
    }
    System.out.println();
    for (int i = heights.length - 2; i >= 0; i--) {  // 6个元素，循环5次比较，从倒数第二个元素往前比较
        // the current index is i 
        int p = i + 1;

        while (p < heights.length && heights[p] >= heights[i]) {
            p = lessFromRight[p];
        }
        lessFromRight[i] = p;
    }
    System.out.println();
    int maxArea = 0;
    for (int i = 0; i < heights.length; i++) {
        maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
    }

    return maxArea;
}
```
