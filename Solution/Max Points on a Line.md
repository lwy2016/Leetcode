Description:

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Solution:

找到同一条直线上最多的点数量

每一个点的坐标为x, y 可以分布在直角坐标系的任意象限内
两个点确定一条直线，如果是3个点在同一条直线上，那么则有 (y3-y1)/(x3-x1) == (y2-y1)/(x2-x1) 即是斜率相同
对于从每一个点p出发，计算该点与其他点的斜率
两种特殊情况：
1.x1 == x2时，为垂直连线，斜率是无穷大
2.x1 == x2, y1 == y2 时两点重合

使用HashMap<Double, Integer> 来存储斜率的个数，如果不将重复的斜率录入进去是要解决的重点:

计算每一个点时，map都是new出来的新对象，res用来记录当前点某一斜率数量最多的次数
samex初始为1，samep初始为0
0/0编译不通过， (double)0/0 得到的值是NaN, java中NaN的定义：public static final double NaN = 0.0d / 0.0;
所以当两点重合的时候，继续进行下面的计算而不是continue;在HashMap中的key值就是NaN

```java
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public int maxPoints(Point[] points) {
    if(points.length <= 0) return 0;
    if(points.length <= 2) return points.length;
    
    int res = 0;
    
    for(int i = 0; i < points.length; i++){   // p点 与其他所有节点比较斜率
        int samex = 1;   // 在同一条垂直线上的点 个数
        int samep = 0;   // 重合点 个数
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();  // 斜率 double, 个数 int
        /*  计算每一个点时，map都是new出来的新对象，res用来记录当前点某一斜率数量最多的次数
            samex初始为1，samep初始为0
            0/0编译不通过， (double)0/0 得到的值是NaN, java中NaN的定义：public static final double NaN = 0.0d / 0.0;
            在HashMap中的key值就是NaN
        */
        for(int j = 0; j < points.length; j++){  
            // 当i != j
            if(i != j){  
                if((points[j].x == points[i].x) && (points[j].y == points[i].y)) samep++;
                if(points[j].x == points[i].x) {
                    samex++;
                    continue;   // 斜率为无穷大，继续下次循环
                }
                // 不论两点重合还是垂直相交，因为continue ，都不会执行后面的代码（第一个if成立，第二个if必成立）
                double d = (double)(points[j].y - points[i].y) / (points[j].x - points[i].x);  // 转为double类型
                if(map.containsKey(d)){
                    map.put(d, map.get(d) + 1);   // 斜率存在，+1
                } else {
                    map.put(d, 2);    // 斜率不存在，置为2
                }
                res = Math.max(res, map.get(d)+samep);
            }
        }
        res = Math.max(res, samex);   // p点 垂直线与其他斜率的最大值
    }
    return res;
}
``` 
