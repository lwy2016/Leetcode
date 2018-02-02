Description:

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

Solution:

n位的格雷码可由n - 1位的格雷码逆推，在最高位前顺序加0，在最高位前逆序加1即可。实际实现中我们可以省去在最高位加0的过程，因为其在数值上和前n - 1位格雷码相同。格雷码的定义，当n = 0时，返回0

```java
    public List<Integer> grayCode(int n) {
        if (n < 0) return null;
        
        List<Integer> currGray = new ArrayList<Integer>();
        currGray.add(0);
        
        // 镜射法
        for (int i = 0; i < n; i++) {
            int code = 1 << i;
            
            for (int j = currGray.size() - 1 ; j >= 0; j--) {
                currGray.add(code | currGray.get(j));
            }
            System.out.println(currGray);
        }
        
        return currGray;
    }
```
