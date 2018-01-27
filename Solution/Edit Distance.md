Description:

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character

Solution:

例如：  让s1和s2变成相同字符串需要下面操作的最小次数。
s1 = “12433” 和s2=”1233”;
则可以通过在s2中间插入4得到12433与s1一致。
即 d(s1, s2) = 1 (进行了一次插入操作)

对于字符串相关的题目十有八九都是用动态规划Dynamic Programming来解

设状态为 f[i][j] 表示 A[0, i] 和 B[0, j] 之间最小的编辑距离 (f[i][j]为字符串1的前i个字符和字符串2的前j个字符的编辑距离)。 
增删操作互为逆操作，即增或者删产生的步数都是一样的。故初始化时容易知道f[0][j] = j, f[i][0] = i.
设 A[0, i] 的形式是 str1c, B[0, j] 的形式是 str2d
如果 c == d 则 f[i][j] = f[i - 1][j - 1]
如果 c != d 则 
 - 如果将 c 替换成 d 则 f[i][j] = f[i - 1][j - 1] + 1
 - 如果在 c 后面添加一个 d 则 f[i][j] = f[i][j - 1] + 1
 - 如果将 c 删除 则 f[i][j] = f[i - 1][j] + 1

二维数组

```java
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <=m; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            f[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    // 三者的最小值 + 1
                    int m_n = Math.min(f[i - 1][j], f[i][j - 1]);
                    f[i][j] = Math.min(f[i - 1][ j - 1], m_n) + 1;
                }
            }
        }
        
        return f[m][n];
    }
```

滚动数组
```java
```

