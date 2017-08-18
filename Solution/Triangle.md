Description:

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
>[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Solution:

动态规划的两个特点：
1. 问题包含最优子结构
2. 子状态可以重复取到
解决此问题的两个要素：
1. 状态方式的选取
2. 状态转移方程

![分析思路](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-120.jpg)

```java
public int minimumTotal(List<List<Integer>> triangle) {
    for (int i = triangle.size() - 2; i >= 0; --i){
        for (int j = 0; j < i + 1; ++j){
            triangle.get(i).set(j,  triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)) );
        }
    }
    return triangle.get(0).get(0);
}
```
