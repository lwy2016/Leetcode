Description:

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.


Example 1:

Input: 2
Output:  2
Explanation:  There are two ways to climb to the top.

1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output:  3
Explanation:  There are three ways to climb to the top.

1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Solution:

此题 其实 可以按照斐波那契 数列来做，但是在 n = 44 的时候 就 Time Limit Exceeded 超时了

```java
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
```

递归太慢了， 那么就使用迭代

```java
    public int climbStairs(int n) {
        int prev = 0;
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            int tmp = curr;
            curr += prev;
            prev = tmp;
        }
        return curr;
    }
```