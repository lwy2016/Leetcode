Description:

Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

Solution:

时间 O(N) 空间 O(1)

思路
我们设想87 / 4，本来应该的得到21余3，那么如果我们把87忽略余数后分解一下，87 = 4 * 21 = 4 * 16 + 4 * 4 + 4 * 1，也就是87 = 4 * (1 * 2^4 + 0 * 2^3 + 1 * 2^2 + 0 * 2^1 + 1 * 2^0)，也就是把商分解为27 = 1 * 2^4 + 0 * 2^3 + 1 * 2^2 + 0 * 2^1 + 1 * 2^0，所以商的二进制是10101。我们可以不断的将4乘2的一次方，二次方，等等，直到找到最大那个次方，在这里是2的四次方。然后，我们就从四次方到零次方，按位看商的这一位该是0还是1。

**任何一个数都可以用二进制表示**

```java
public int divide(int dividend, int divisor){  
    if(divisor == 0) return Integer.MAX_VALUE;
    if(dividend == Integer.MIN_VALUE && (divisor == 1 || divisor == -1)){
        return divisor == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
    if(divisor == 1) return dividend;   //  防止超时
    if(divisor == -1) return -dividend;

    return (int)divideLong(dividend, divisor); // 转为 int 型
}
public long divideLong(long dd, long dv){  // long 型
    boolean isPos = (dd > 0 && dv > 0) || (dd < 0 && dv < 0);
    dd = Math.abs(dd);
    dv = Math.abs(dv);
    
    int digits = 0;
    long res = 0;
    // 通过将除数乘2，乘4，乘8，一直乘下去，找到商的最高的次方
    // 比如87/4=21，商的最高次方是4，即2^4=16，即4 * 16 < 87
    while(dd >= dv){
        dv <<= 1;
        digits++;
    }
    // 重置除数，把最高次方减1得到实际最高位，刚才循环中多加了一次
    dv >>= digits;
    digits--;
    // 看商的每一位是否应该为1
    while(digits >= 0){
        if( dd >= (dv << digits) ){  // 二进制 要么为0，要么为1，为1则加上该值
            dd -= dv << digits;
            res += 1 << digits;
        }
        digits--;
    }
    return isPos?res:-res;
}
```
