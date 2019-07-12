Description:

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Solution:

设状态 f[i][j] 表示 s1[0, i] 和 s2[0, j] 匹配 s3[0, i + j]
如果 s1 的最后一个字符等于 s3 的最后一个字符， 则 f[i][j] = f[i - 1][j]
如果 s2 的最后一个字符等于 s2 的最后一个字符， 则 f[i][j] = f[i][j - 1]
因此，状态转移方程如下：
f[i][j] = (s1[i - 1] == s3[i + j - 1] && f[i - 1][j]) || (s2[j - 1] == s3[i + j - 1] && f[i][j - 1])

二维动归
```java
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = (s1 == null) ? 0 : s1.length();
        int len2 = (s2 == null) ? 0 : s2.length();
        int len3 = (s3 == null) ? 0 : s3.length();
        
        if (len3 != len1 + len2) return false;
        
        boolean[][] f = new boolean[len1 + 1][len2 + 1];
        f[0][0] = true;
        
        // 处理第一列
        for (int i = 1; i < len1 + 1; i++) {
            f[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1) && f[i - 1][0];
        }
        // 处理第一行
        for (int i = 1; i < len2 + 1; i++) {
            f[0][i] = s2.charAt(i - 1) == s3.charAt(i - 1) && f[0][i - 1];
        }
        
        // i >= 1, j >= 1
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                boolean case1 = s1.charAt(i - 1) == s3.charAt(i + j - 1) && f[i -1][j];
                boolean case2 = s2.charAt(j - 1) == s3.charAt(i + j - 1) && f[i][j - 1];
                f[i][j] = case1 || case2;
            }
        }
        
        return f[len1][len2];
    }
```


