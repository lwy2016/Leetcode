Description:

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

Solution:

This can be solved by two points:

cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
The 2nd point reminds us of using dp (caching).

>a    b    a    |    c    c
                     j   i
          j-1   |   [j, i] is palindrome
 then cut(j-1)  +   1

判定字符串是否是回文数，用到了动态规划:
若是一个字母或者是两个相邻且相等的字母，则这一小段是回文数，则标记该区间为pal[j][i] = true，判定代码 if(c[j] == c[i] && j +1 > i -1)
若是字符串比较长了，判断该串的两端是否相等，若相等，且他们之间的区间也是回文数的话(pal[j+1][i-1])，则标记该区间为pal[j][i] = true，判定代码 if(c[j] == c[i] && pal[j+1][i-1])

cut[] 记录每次外层循环的分割次数，min初始为最坏的情况下，分割的次数，即n个字母，要分割n-1次

![分析思路](http://7xnyvm.com1.z0.glb.clouddn.com/LeeCode-132.jpg)

```java
public int minCut(String s){
    char[] c = s.toCharArray();
    int n = c.length;
    int[] cut = new int[n];
    boolean[][] pal = new boolean[n][n];
    
    for (int i = 0; i < n; i++){
        int min = i;
        for(int j = 0; j <= i; j++){
            if( c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1]) ){
                pal[j][i] = true;
                min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
            }
        }
        cut[i] = min;
    }
    return cut[n - 1];
}
```