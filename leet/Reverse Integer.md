Description:

Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

Note:
The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.

有符号int型的数字区间为，[-2^31, 2^31-1]，即[-2147483648, 2147483647]

Solution1:
```java
public class Solution(){
	public int reverse(int x){
		int sign = x >= 0 ? 1 : -1;    
		x = x * sign;					// 计算过程中，始终将传入的值置为正数
		int res = 0;
		while( x > 0 ){
			if( Integer.MAX_VALUE / 10 < res || (Integer.MAX_VALUE - x % 10) < res * 10){
			/* 变一下型 即是 if( Integer.MAX_VALUE < res * 10 || Integer.MAX_VALUE < res * 10 + x % 10) 
			*  上面不等式的右边是每轮的结果，但是因为已经超过Integer.MAX_VALUE，为了避免溢出	
			*/
				return 0;
			}
			res = res * 10 + x % 10;    // 反转处理
			x = x / 10;
		}
		return res * sign;
	}
}
```

Solution2:
使用**long型**表示反转过程中的值rev，判断rev是否在[Integer.MIN_VALUE, Integer.MAX_VALUE] 区间内，若不在，则视为溢出
```java
public class Solution {
    public int reverse(int x) {
		long rev = 0;
		while(x != 0){
			rev = rev * 10 + x % 10;   // 反转处理
			x = x / 10;
			if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE ){  // 或  if( rev != (int)rev )
				return 0;   // 溢出
			}
		}
		return int(rev);
	}
}
```
