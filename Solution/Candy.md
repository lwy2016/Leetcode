Description:

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

 - Each child must have at least one candy.
 - Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Solution:

当孩子的rate是非递减曲线的时候：
如果5个孩子的rate分别是 1, 2, 5, 7, 10 那么糖果数是 1, 2, 3, 4, 5 
如果5个孩子的rate分别是 1, 2, 5, 5, 10 那么糖果数是 1, 2, 3, 1, 2

当孩子的rate是递减曲线的时候：
如果5个孩子的rate分别是 1, , 4, 3, 2 遍历时，第一个孩子糖果数为1，第2个孩子是2，那么第3个孩子应该是几？可知第2个孩子的糖果数应为 4 个。有没有方法在遍历到后面时，能够计算出一个修正值

从左到右扫描一遍，使得右边比左边得分高的孩子（非递减）比左边多
从右到左扫描一遍，使得左边比右边得分高的孩子（递减）比右边多

```java 
public int candy(int[] ratings){
    int n = ratings.length;
    if(n == 0) return -1;
    if(n == 1) return 1;

    int result = 0;
    int[] cans = new int[n];
    cans[0] = 1;

    // 从左往右扫描，如果right > left ，则right比left多1，否则置为1
    for(int i = 1; i < n; i++){
        if(ratings[i-1] < ratings[i]){
            cans[i] = cans[i-1] + 1;
        } else {
            cans[i] = 1;
        }
    }
    // 从右往左扫描，如果left > right，且left糖果比right少，那么left比right多1
    for(int i = n - 2; i >= 0; i--){
        // 2,4,5,6,7, 10, 5,3,1
        // 1,2,3,4,5, 6,  3,2,1
        if(ratings[i] > ratings[i+1] && cans[i] <= cans[i+1]){
            cans[i] = cans[i+1] + 1;
        } 
    }
    for(int i = 0; i < n; i++){
        result += cans[i];
    }
    return result;
}
```