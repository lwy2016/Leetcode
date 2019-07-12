Description:

Implement int sqrt(int x).

Compute and return the square root of x.

x is guaranteed to be a non-negative integer.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.

Solution:

```java
    public int mySqrt(int x) {
        int left = 1, right = x / 2;
        int last_mid = 1; 
        
        if (x < 2) return x;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(x / mid > mid) {
                left = mid + 1;
                last_mid = mid;
            } else if(x / mid < mid) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return last_mid;
    }
```
