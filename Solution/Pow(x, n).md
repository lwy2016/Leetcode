Description:

Implement pow(x, n).

Solution:

用递归来折半计算，每次把n缩小一半，这样n最终会缩小到0，任何数的0次方都为1，这时候我们再往回乘，如果此时n是偶数，直接把上次递归得到的值算个平方返回即可，如果是奇数，则还需要乘上个x的值。还有一点需要引起我们的注意的是n有可能为负数，对于n是负数的情况，我们可以先用其绝对值计算出一个结果再取其倒数即可

```java
public double myPow(double x, int n){
    if(n < 0) return power(x, -n);
    return power(x, n);
}
public double power(double x, int n){
    if(n == 0) return 1;
    double half = power(x, n / 2);
    if(n % 2 == 0) return half * half;
    return x * half * half;
}
```

