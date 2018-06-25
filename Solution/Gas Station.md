Description:

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.

```
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
```

```
Example 2:

Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
```

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