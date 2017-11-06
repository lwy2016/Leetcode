Description:


Solution:

如果所有的gas-cost小于0，那么没有解，返回 -1;
如果前面所有的gas-cost加起来都小于0，那么前面所有的点都不能作为出发点，return -1;

 - 记录最后一个加起来小于零的索引，然后返回这个 索引+1 就是答案了
```java
public int canCompleteCircuit(int[] gas, int[] cost) {
    // 找到最后一个gas-cost<0的点，返回它的下一个点
    int j = -1;   // 初始为-1,当起始点符合条件的时候j+1的为0
    int sum = 0;
    int total = 0;  // 总体gas-cost
    for(int i = 0; i < gas.length; i++){
        sum += gas[i] - cost[i];
        total += gas[i] - cost[i];
        if(sum < 0){
            j = i;
            sum = 0;
        }
    }
    if(total < 0) return -1;
    return j+1;
}
```

 - 跳跃式，跃过不能作为出发点的点，加速循环
```java 
// If start from i, stop before station x --> no station k from i+1 to x-1 can reach x.
// thus i can jump directly to x instead of i+1, bringing complexity from O(n^2) to O(n)
public int canCompleteCircuit(int[] gas, int[] cost) {
    int i, x, n = gas.length;
    
    for(i = 0; i < n; ){
        int leftGas = 0;
        // calcus from i to x
        for(x = 0; x < n; x++){
            int k = (i + x) % n;   // 循环一圈 i <= k <= x 
            leftGas += gas[k] - cost[k];
            if(leftGas < 0) break;   // when k == x --> leftGas < 0 
        }
        if(x >= n) return i;
        i += x+1;
    }
    return -1;
}
```