Description:

Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".

Solution:

吼吼
```java
    public String addBinary(String a, String b) {
        // 反转 String 字符串
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        int m = a.length();
        int n = b.length();
        StringBuilder sb = new StringBuilder();
        int min = m < n ? m : n;
        int max = m > n ? m : n;
        int carry = 0;
        // a,b同长度的部分
        for (int i = 0; i < min; i++) {
            int x = a.charAt(i) - '0';
            int y = b.charAt(i) - '0';
            int x_y = (x + y + carry) % 2;
            sb.append(x_y);
            carry = (x + y + carry) / 2;
        }
        // a,b不同长度的部分
        for (int i = min; i < max; i++) {
            int x = 0;
            if (m > min) {
                x = a.charAt(i) - '0';
            } else {
                x = b.charAt(i) - '0';
            }
            int x_y = (x + carry) % 2;
            sb.append(x_y);
            carry = (x + carry) / 2;
        }
        if (carry == 1) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }
```