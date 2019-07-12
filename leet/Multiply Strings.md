Description:

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

Solution:

两个数相乘所得的乘积的长度为乘数长度之和，且有可能是首位数字是0(即，长度之和-1)

```
public String multiply(String num1, String num2) { 
    if (num1.equals("0") || num2.equals("0")) {
        return "0";
    }

    int[] arr = new int[num1.length() + num2.length()];

    for (int i = num1.length() - 1; i >= 0; i--) {
        for (int j = num2.length() - 1; j >= 0; j--) {
            int multiply = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            // 两个指针指向生成的两位数在数组中的位置
            int pos1 = i + j;       // 十位
            int pos2 = i + j + 1;   // 个位
            multiply += arr[pos2];  // 两位数加上原数组中对应到两位数个位上的数字
            arr[pos1] += multiply / 10;     // 两位数十位要加上原数组对应位置上的数字
            arr[pos2] = multiply % 10;      // 两位数的个位已事先加上了原数组在对应位置上的数字
        }
    }
    StringBuilder sb = new StringBuilder();
    if (arr[0] != 0) {      // 首位不为0则加入
        sb.append(arr[0]);
    }
    for (int i = 1; i < arr.length; i++) {
        sb.append(arr[i]);
    }

    return sb.toString();
}
```

