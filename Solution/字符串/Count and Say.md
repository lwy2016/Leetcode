Description:

The count-and-say sequence is the sequence of integers with the first five terms as following:

> 1.     1
> 2.     11
> 3.     21
> 4.     1211
> 5.     111221

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

>Input: 1
Output: "1"

Example 2:

>Input: 4
Output: "1211"

Solution:

本题使用 **递归** 来做

![Count and Say](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-38.jpg)

```java
public String countAndSay(int n){
	if(n == 1){    // 初始值
		return "1";
	}
	String s = countAndSay(n - 1);   // 递归开始，每次调用比他小的值
	StringBuilder sb = new StringBuilder();
	int count = 0, L = s.length();   // count 计算相邻的相同字符出现的次数

	for(int i = 0; i < L; i++){
		count++;      // 每进入一次循环，count+1
		// i走到右边界 或 相邻的两个字符不相等时
		if(i == L - 1 || (i < L -1 && s.charAt(i) != s.charAt(i + 1))){ 
			sb.append(count);
			sb.append(s.charAt(i));

			count = 0;   // 清0，下一个字符的计算
		}
	}

	return sb.toString();
}
```